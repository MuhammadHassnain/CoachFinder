package com.coachfinder.security.auth.user;

import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.coachfinder.security.auth.AuthRequest;
import com.coachfinder.security.auth.appuser.UserRole;
import com.coachfinder.security.auth.role.Role;
import com.coachfinder.security.auth.role.RoleService;

@Component
public class UserMapper {
	
	
	 private static PasswordEncoder passwordEncoder;	
	
	
	@Autowired
	public UserMapper(PasswordEncoder passwordEncoder) {
		UserMapper.passwordEncoder = passwordEncoder;
	}
	
	public static UserDto toUserDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setRoles(user.getRoles());
		return userDto;
	}
	

	public static UserDto toUserDto(User user,boolean include_status, boolean include_password) {
		
		UserDto userDto = new UserDto();
//		userDto.setUsername(user.getUsername());
		if(include_password) {
			userDto.setPassword(user.getPassword());
		}
		if (include_status) {
			userDto.setAccountNonExpired(user.isAccountNonExpired());
			userDto.setAccountNonLocked(user.isAccountNonLocked());
			userDto.setEnabled(user.isEnabled());
			userDto.setCredentialsNonExpired(user.isCredentialsNonExpired());
		}
		userDto.setEmail(user.getEmail());
		userDto.setRoles(user.getRoles());
		return userDto;
	}
	
	public static User authToUser(AuthRequest.CreateUserRequest request) {
//		List<String> user_roles = UserRole.COACH.getRoleWithPermissions().stream()
//				.map(item -> item.getName())
//				.collect(Collectors.toList());
//		Set<Role> roles = roleService.findByNameIn(user_roles);
//		
//		UserDto userDto = new UserDto();
////		userDto.setUsername(request.getUsername());
//		userDto.setPassword(passwordEncoder.encode(request.getPassword()));
//		userDto.setEmail(request.getEmail());
//		userDto.setRoles(roles);
//		
//		userDto.setAccountNonExpired(true);
//		userDto.setAccountNonLocked(true);
//		userDto.setCredentialsNonExpired(true);
//		userDto.setEnabled(true);
		System.out.println(passwordEncoder.encode(request.getPassword()));
		User user = new User(passwordEncoder.encode(request.getPassword()),request.getEmail(),true,true,true,true,null);
		
		return user;
		
		
		
		


	}

}
