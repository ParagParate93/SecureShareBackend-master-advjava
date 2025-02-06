package com.cdac.trustvault.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OtpApiResponse {

    private boolean success; 
    private String message;
    private LocalDateTime timeStamp;
    private String role;
    private String token;
    private String name;
    private String email;
    
  
    public OtpApiResponse(boolean success, String message, String role, String token,String name,String email) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.role = role;
        this.name = name;
        this.email = email;
        this.timeStamp = LocalDateTime.now();
    }

  
    public OtpApiResponse(boolean success, String message, String role) {
        this.success = success;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
        this.role = role;
    }
}
