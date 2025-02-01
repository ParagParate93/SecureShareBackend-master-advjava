package com.cdac.trustvault.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users") // to specify name of the table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = { "password" })

public class UserEntity extends BaseEntity {


	@Column(length = 20) 
	private String name;
	

	@Column(length = 50, unique = true) 
	private String email;
	
	@Column(length = 500, nullable = false)
	private String password;
	
	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;

	
	@Enumerated(EnumType.STRING) 
	@Column(length = 30) 
	private UserRole role;
	
	

}
