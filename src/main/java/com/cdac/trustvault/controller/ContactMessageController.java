package com.cdac.trustvault.controller;
//package com.trustvault.controller;

import com.cdac.trustvault.entity.ContactMessage;
import com.cdac.trustvault.repository.ContactMessageRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactus")
@CrossOrigin(origins = "http://localhost:5173") // Adjust for frontend
public class ContactMessageController {

    private final ContactMessageRepository repository;

    public ContactMessageController(ContactMessageRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitContactForm(@Valid @RequestBody ContactMessage message) {
        repository.save(message);
        return ResponseEntity.ok("Message submitted successfully.");
    }

    @GetMapping("/messages")
    public List<ContactMessage> getAllMessages() {
        return repository.findAll();
    }
}
