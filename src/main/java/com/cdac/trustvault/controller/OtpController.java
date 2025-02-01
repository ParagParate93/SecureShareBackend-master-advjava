package com.cdac.trustvault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.trustvault.dto.OtpApiResponse;
import com.cdac.trustvault.dto.OtpDto;
import com.cdac.trustvault.dto.OtpResDto;
import com.cdac.trustvault.services.OtpService;

@RestController
@RequestMapping("/otp")
@CrossOrigin(origins = "http://localhost:5173") // Change to your frontend's URL
public class OtpController {

	@Autowired
	private OtpService otpService;

	@PostMapping("/verifyotp")
	public ResponseEntity<?> verifyOtp(@RequestBody OtpDto reqDto) {
		try {
			OtpResDto respDto = otpService.verifyOtp(reqDto);
			String res = respDto.getRole().toString();
			return ResponseEntity.ok(new OtpApiResponse(true, "OTP verified successfully", res));
		} catch (Exception ex) {
			return ResponseEntity.status(400).body(new OtpApiResponse(false, ex.getMessage(), null));
		}
	}

}
