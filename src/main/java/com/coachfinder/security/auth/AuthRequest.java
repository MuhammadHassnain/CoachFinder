package com.coachfinder.security.auth;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

public class AuthRequest {


	
	@Getter
	@Setter
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@NonNull
	public static class CreateUserRequest {
	
		
		private String email;
		
		private String password;
		
		
		

	}
}
