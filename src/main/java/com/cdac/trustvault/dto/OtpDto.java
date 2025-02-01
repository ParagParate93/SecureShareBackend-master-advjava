package com.cdac.trustvault.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OtpDto {
    @NotBlank(message="Email is required")
	private String email;
    //private String password;
    private String otp; // For OTP validation

    // Getters and Setters
}
