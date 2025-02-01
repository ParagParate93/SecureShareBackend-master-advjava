package com.cdac.trustvault.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "otp")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Otp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CreationTimestamp
	@Column(name="generated_on")
	private LocalDateTime generationTime;
	
	@Column(name="expired_on")
	private LocalDateTime expirationTime;
	
	@Column(name="otp")
	private String otp;
	
	@Column(name="email")
	private String email;
	
	private String role;

	public Otp(String otp, String email) {
		this.otp = otp;
		this.email = email;
	}
}
	
	
