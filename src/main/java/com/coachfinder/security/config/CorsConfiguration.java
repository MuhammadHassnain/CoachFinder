package com.coachfinder.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.net.HttpHeaders;

@Configuration
public class CorsConfiguration {
	

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// TODO Auto-generated method stub
				registry.addMapping("/**").allowedOrigins("*").allowedHeaders(HttpHeaders.AUTHORIZATION, "content-type", "x-auth-token")
				.exposedHeaders(HttpHeaders.AUTHORIZATION);
			}
			
		};
		
	}	

}
