package com.kishore.whatsapp.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class AuthResponse {

	private String jwt;
	private boolean isAuth;
	
}
