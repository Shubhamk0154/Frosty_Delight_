package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
//	public AuthenticationResponse(String string, String email2, String password2, String generateJwtToken) {
//		// TODO Auto-generated constructor stub
//	}
	private String role;
	
	private String email;
	private String password;
	private final String jwt;
	
	
}
