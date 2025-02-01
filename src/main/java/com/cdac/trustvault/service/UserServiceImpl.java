package com.cdac.trustvault.service;


import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.trustvault.dao.UserDao;
import com.cdac.trustvault.dao.UserrRepository;
import com.cdac.trustvault.entity.UserEntity;


@Service
public class UserServiceImpl implements UserService{

	   @Autowired
	   private UserDao userDao;
	
 	   @Autowired
	   private ModelMapper mapper;
 	   
 	  
 	
	  @Autowired
	    private UserrRepository userRepository;

	    public List<UserEntity> getAllUsers() {
	        return userRepository.findAll();
	    }

	    public Optional<UserEntity> getUserById(Long id) {
	        return userRepository.findById(id);
	    }

	    public UserEntity createUser(UserEntity user) {
	        return userRepository.save(user);
	    }

	    public UserEntity updateUser(Long id, UserEntity user) {
	        if (userRepository.existsById(id)) {
	            user.setId(id);
	            return userRepository.save(user);
	        }
	        return null;
	    }

	    public boolean deleteUser(Long id) {
	        if (userRepository.existsById(id)) {
	            userRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }

	}
