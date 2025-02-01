package com.cdac.trustvault.dto;

import com.cdac.trustvault.entity.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRespDTO extends BaseDTO {
	
	private String name;
	private String email;
	private UserRole role;
	
	
	public UserRespDTO(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	
	
}
