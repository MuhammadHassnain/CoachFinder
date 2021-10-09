package com.coachfinder.security.auth.appuser;

public enum UserPermission {
	
	
	COACH_READ("coach:read"),
	COACH_WRITE("coach:write"),
	REQUEST_READ("request:read"),
	REQUEST_WRITE("request:write"),
	ADMIN_READ("admin:read"),
	ADMIN_WRITE("admin:write");
	
	private final String permission;
	
	private UserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
	
	

}
