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
    
  
    public OtpApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }

  
    public OtpApiResponse(boolean success, String message, String role) {
        this.success = success;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
        this.role = role;
    }
}
