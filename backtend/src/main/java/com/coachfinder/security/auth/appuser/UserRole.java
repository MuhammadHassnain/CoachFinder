package com.coachfinder.security.auth.appuser;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.coachfinder.security.auth.role.Role;
import com.google.common.collect.Sets;

public enum UserRole {
	
	


	ADMIN(Sets.newHashSet(
			UserPermission.COACH_WRITE,
			UserPermission.COACH_READ,
			UserPermission.ADMIN_READ,
			UserPermission.ADMIN_WRITE)),
	COACH(Sets.newHashSet(
			UserPermission.COACH_READ,
			UserPermission.COACH_WRITE,
			UserPermission.REQUEST_READ,
			UserPermission.REQUEST_WRITE
			));
	
	
	
	private final Set<UserPermission> permissions;

	UserRole(Set<UserPermission> permissions) {
		this.permissions = permissions;	
	}

	public Set<UserPermission> getPermissions() {
		return permissions;
	}
	
	public Set<Role> getRoleWithPermissions(){
		Set<Role> collections = getPermissions()
				.stream()
				.map(p -> new Role(p.getPermission().toString()))
				.collect(Collectors.toSet());
		collections.add(new Role("ROLE_" + this.name()));
		return collections;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		// TODO Auto-generated method stub
		Set<SimpleGrantedAuthority> collections = getPermissions()
													.stream()
													.map(p -> new SimpleGrantedAuthority(p.getPermission()))
													.collect(Collectors.toSet());
		collections.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return collections;
	}

}
