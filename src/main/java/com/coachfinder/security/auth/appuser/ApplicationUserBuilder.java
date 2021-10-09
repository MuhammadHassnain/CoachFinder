package com.coachfinder.security.auth.appuser;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.coachfinder.security.auth.role.Role;



public  class ApplicationUserBuilder {
	
	
	private String email;
	private String password;
	private Set<? extends GrantedAuthority> authorities;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;
	
	private ApplicationUserBuilder() {
		
	}
	
	public AppUser build() {
		return new AppUser(email, password, authorities, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
	}
	
	public static ApplicationUserBuilder builder() {
		return new ApplicationUserBuilder();
	}
	
	public ApplicationUserBuilder username(String email) {
		this.email = email;
		return this;
	}
	
	public ApplicationUserBuilder password(String password) {
		this.password = password;
		return this;
	}
	
	public ApplicationUserBuilder authorities(Set<Role> roles) {
		Set<SimpleGrantedAuthority> collections = roles
				.stream()
				.map(p -> new SimpleGrantedAuthority(p.getName()))
				.collect(Collectors.toSet());
		this.authorities = collections;
		return this;
	}
	
	public ApplicationUserBuilder isAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
		return this;
	}
	public ApplicationUserBuilder isAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
		return this;
	}
	public ApplicationUserBuilder isCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		return this;
	}
	public ApplicationUserBuilder isEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		return this;
	}

}
