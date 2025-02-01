package com.cdac.trustvault.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.trustvault.custom_exceptions.ResourceNotFoundException;
import com.cdac.trustvault.dto.AuthRequest;
import com.cdac.trustvault.dto.PasswordResetRequest;
import com.cdac.trustvault.dto.UserRespDTO;
import com.cdac.trustvault.entity.UserEntity;
import com.cdac.trustvault.repository.UserRepository;

@Service
@Transactional
public class OTPUserServiceImpl implements OTPUserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public String addNewUser(UserEntity newUser) {
		UserEntity user = userRepo.save(newUser);
		return "new user added with ID=" + user.getId() + " " + user.getRole();
	}

	@Override
	public UserRespDTO authenticate(AuthRequest dto) {
		Optional<UserEntity> optional = userRepo.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
		UserEntity userEntity = optional
				.orElseThrow(() -> new ResourceNotFoundException("Invalid email or password !!!!"));

		return mapper.map(userEntity, UserRespDTO.class);
	}

	public void updatePassword(PasswordResetRequest passResDTO) {
		Optional<UserEntity> optional = userRepo.findByEmail(passResDTO.getEmail());
		UserEntity userEntity = optional.orElseThrow(() -> new ResourceNotFoundException("Invalid email or otp !!!!"));
		if (optional.isPresent()) {
			userEntity.setPassword(passResDTO.getNewPassword());
			userRepo.save(userEntity);
		} else {
			throw new RuntimeException("User not found");
		}

	}
}
