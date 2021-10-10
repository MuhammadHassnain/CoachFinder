package com.coachfinder.security.auth.user;






public interface UserService {
	
	User createUser(User user);
	User findUserByEmail(String email);
	
	User updateUser(User user);

}
