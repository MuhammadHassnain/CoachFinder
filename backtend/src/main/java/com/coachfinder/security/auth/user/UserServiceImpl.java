package com.coachfinder.security.auth.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	
	@Autowired UserRepo userRepo;
	
	//@Autowired PasswordEncoder passwordEncoder;
	
	
	@Override
	public User createUser(User user) {
		Optional<User> u = userRepo.findByUserName(user.getEmail());
		if(u.isPresent()) {
			throw new  DuplicateKeyException("User with Email "+user.getEmail() + "Already Exists");
		}
		return userRepo.save(user);
	}
	
	
	@Override	
	public User findUserByEmail(String email){
		Optional<User> user = userRepo.findByUserName(email);
		if(user.isPresent()) {
			
			return user.get();
		}
		return null;
	}


	@Override
	public User updateUser(User user) {
		
		return userRepo.save(user);
	}

}
