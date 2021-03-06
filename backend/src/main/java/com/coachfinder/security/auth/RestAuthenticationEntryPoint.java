package com.coachfinder.security.auth;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import io.jsonwebtoken.io.IOException;

public class RestAuthenticationEntryPoint  implements AuthenticationEntryPoint{

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
    
    	
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
       
        try {
			response.getOutputStream().println("{ \"error\": \"" + authenticationException.getMessage() + "\" }");

		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }


}
