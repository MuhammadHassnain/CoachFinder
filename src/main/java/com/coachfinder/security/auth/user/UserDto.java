package com.coachfinder.security.auth.user;

import java.util.HashSet;
import java.util.Set;


import com.coachfinder.security.auth.role.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class UserDto {
	
	private Integer id;
	
	private String email;
	
	private String password;
	
	private boolean isAccountNonExpired;
	
	private boolean isAccountNonLocked;
	
	private boolean isCredentialsNonExpired;
	
	private boolean isEnabled;

	private Set<Role> roles = new HashSet<Role>();

	

}
