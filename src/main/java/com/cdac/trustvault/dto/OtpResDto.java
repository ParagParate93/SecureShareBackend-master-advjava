package com.cdac.trustvault.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OtpResDto extends BaseDTO{
	private String name;
	private String email;
	private String role;
	
	
	public OtpResDto(String name, String email,String role) {
		this.name = name;
		this.email = email;
		this.role = role;
	}

}
