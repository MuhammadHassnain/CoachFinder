package com.coachfinder.security.auth.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.coachfinder.utils.DateUtils;
import com.coachfinder.utils.Utils;
import com.coachfinder.utils.dto.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;

public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authManager;
	
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;

	
	
	
	public JwtUsernameAndPasswordAuthFilter(AuthenticationManager authManager,JwtConfig jwtConfig,SecretKey secretKey) {
		this.authManager = authManager;
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
		
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
		
		try {
			UsernameAndPasswordAuthRequest authRequest = new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthRequest.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
			
			return this.authManager.authenticate(authentication);
			
		} 
		catch (IOException e) {

			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			throw new RuntimeException(e);
		}
		catch(BadCredentialsException e) {
			response.setContentType("application/json");			
			throw new RuntimeException("Wrong Password");
			}
		catch(AuthenticationException e) {
			throw new RuntimeException(e.getMessage());

		}
		
	}

	
	
	
	





	@Override
	protected void successfulAuthentication(HttpServletRequest request,
											HttpServletResponse response,
											FilterChain chain,
											Authentication authResult)
											
													throws IOException, ServletException {
		
		SecretKey  key = secretKey;
		Date expireDate = java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationDay()));
		String token = Jwts.builder().setSubject(authResult.getName())
						.claim("authorities", authResult.getAuthorities())
						.setIssuedAt(new Date())
						.setExpiration(expireDate)
						.signWith(key)
						.compact();
	

		response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
		
		HashMap<String, String> res_map = new HashMap<String, String>();
		res_map.put(jwtConfig.getAuthorizationHeader(),  token);
		
		res_map.put("userId",authResult.getName()); 
		res_map.put("tokenExpiration",DateUtils.formattedDate(expireDate));
		
		
		Response<Object> payload = Response.ok().setPayload(res_map);
		
		
		response.setContentType("application/json");
		response.getWriter().write(Utils.convertObjectToJson(payload));

		
		
	}
	
	
	
	
	
	

}
