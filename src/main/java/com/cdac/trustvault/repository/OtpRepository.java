package com.cdac.trustvault.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.trustvault.entity.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long>{
	
	Optional<Otp> findByEmailAndOtp(String em,String otp);
	
}
