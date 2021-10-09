package com.coachfinder.security.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.coachfinder.security.auth.RestAuthenticationEntryPoint;
import com.coachfinder.security.auth.appuser.AppUserService;
import com.coachfinder.security.auth.appuser.UserPermission;
import com.coachfinder.security.auth.appuser.UserRole;
import com.coachfinder.security.auth.jwt.JwtConfig;
import com.coachfinder.security.auth.jwt.JwtTokenVerifier;
import com.coachfinder.security.auth.jwt.JwtUsernameAndPasswordAuthFilter;



@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	private final PasswordEncoder passwordEncoder; 
	private final AppUserService appUserService;
	
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;
	
	@Autowired
	public ApplicationSecurityConfiguration(AppUserService appUserService
											,PasswordEncoder passwordEncoder
											,JwtConfig jwtConfig
											,SecretKey secretKey) {
		super();
		this.appUserService = appUserService;
		this.passwordEncoder = passwordEncoder;
		this.secretKey = secretKey;
		this.jwtConfig = jwtConfig;
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.cors().and().csrf().disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.exceptionHandling()
				.authenticationEntryPoint(new RestAuthenticationEntryPoint())
			.and()
				.addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager(),jwtConfig,secretKey))
				.addFilterAfter(new JwtTokenVerifier(jwtConfig,secretKey), JwtUsernameAndPasswordAuthFilter.class)
			.authorizeRequests()
				.antMatchers("/","/user","/login","/signup","index","/css/*","/js/*").permitAll()
				.antMatchers(HttpMethod.GET,"/api/coaches").permitAll()
				.antMatchers(HttpMethod.POST,"/api/coaches").permitAll()
				.antMatchers(HttpMethod.POST,"/api/requests").permitAll()
				.antMatchers("/api/coaches/profile").hasRole(UserRole.COACH.name())
				.antMatchers("/api/coaches/requests")
					.hasAnyAuthority(UserPermission.REQUEST_READ.getPermission(),UserPermission.REQUEST_WRITE.getPermission())
			.anyRequest()
			.authenticated();
		
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(this.appUserService).passwordEncoder(this.passwordEncoder);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
