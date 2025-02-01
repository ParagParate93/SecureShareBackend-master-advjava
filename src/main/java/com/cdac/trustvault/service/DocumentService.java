package com.cdac.trustvault.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.trustvault.entity.Document;
import com.cdac.trustvault.entity.DocumentSharing;
import com.cdac.trustvault.repository.DocumentRepository;
import com.cdac.trustvault.repository.DocumentSharingRepository;
import com.cdac.trustvault.util.FileEncryptionUtil;

import jakarta.transaction.Transactional;

@Service
public class DocumentService {
	
	 	@Autowired
	    private DocumentRepository documentRepository;
	 	
	 	@Autowired
	 	private DocumentSharingRepository documentSharingRepository;

	    @Autowired
	    private EmailService emailService;

	    public Document uploadDocument(MultipartFile file,String uploadedBy,String uploaderEmail) throws Exception {
	        SecretKey key = FileEncryptionUtil.generateKey();
	        byte[] encryptedContent = FileEncryptionUtil.encrypt(file.getBytes(), key);

	        Document document = new Document();
	        document.setName(file.getOriginalFilename());
	        document.setType(file.getContentType());
	        document.setSize(file.getSize());
	        document.setUploadedAt(LocalDateTime.now());
	        document.setEncryptedContent(encryptedContent);
	        document.setEncryptionKey(FileEncryptionUtil.encodeKey(key));
	        document.setUploadedBy(uploadedBy);
	        document.setUploaderEmail(uploaderEmail);

	        return documentRepository.save(document);
	    }

	    public byte[] downloadDocument(Long id) throws Exception {
	        Document document = documentRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Document not found"));
	        SecretKey key = FileEncryptionUtil.decodeKey(document.getEncryptionKey());
	        return FileEncryptionUtil.decrypt(document.getEncryptedContent(), key);
	    }

	    public List<Document> getDocumentsByUploader(String uploadedBy, String uploaderEmail) {
	    	List<Document> uploadedDocuments = documentRepository.findByUploadedByAndUploaderEmail(uploadedBy, uploaderEmail);
	        List<DocumentSharing> sharedDocumentsInfo = documentSharingRepository.findBySharedWith(uploaderEmail);
	        List<Long> sharedDocumentIds = sharedDocumentsInfo.stream()
	                .map(DocumentSharing::getDocumentId)
	                .collect(Collectors.toList());
	        
	        
	        List<Document> sharedDocuments = documentRepository.findAllById(sharedDocumentIds);
	        
	        for (Document document : sharedDocuments) {
	            document.setShared(true); 
	        }
	        uploadedDocuments.addAll(sharedDocuments);
	        
	        return uploadedDocuments;
	    }
	    
	    @Transactional
	    public boolean deleteDocument(Long id) {
	        try {
	            Document document = documentRepository.findById(id).orElse(null);
	            if (document != null) {
	                documentRepository.delete(document); // Delete the document from the repository
	                return true;
	            }
	            return false;
	        } catch (Exception e) {
	            throw new RuntimeException("Error occurred while deleting document", e);
	        }
	    }
	    
	    @Transactional
	    public boolean shareDocument(DocumentSharing request,LocalDateTime sharedAt) {
	    	   String loginLink = "http://localhost:5173/login";  
	    	    String subject = "Document Shared with You: " + request.getDocumentName() + " by "+request.getSharedBy();  // Subject with document name and shared by info
	    	    String body = String.format("Hello,\n\n%s has shared the document \"%s\" with you on TrustVault.\n\nPlease log in to your TrustVault account to view the document.\n\nLogin here: %s", 
                        request.getSharedBy(), request.getDocumentName(), loginLink);
	    	    //  http://localhost:8080/documents/download/15
	    	    boolean emailSent = emailService.sendEmail(request.getSharedWith(), subject, body);

	    	    if (!emailSent) {
	    	        System.err.println("Failed to send email to " + request.getSharedWith());
	    	    } else {
	    	        System.out.println("Email sent successfully to " + request.getSharedWith());
	    	    }
	    	    return emailSent;
	    }
}
