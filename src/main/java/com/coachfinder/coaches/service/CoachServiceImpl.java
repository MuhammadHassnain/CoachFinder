package com.coachfinder.coaches.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coachfinder.coaches.entity.Coach;
import com.coachfinder.coaches.repository.CoachRepo;
import com.coachfinder.security.auth.user.User;
import com.coachfinder.security.auth.user.UserRepo;



@Service
public class CoachServiceImpl  implements CoachService{

	@Autowired CoachRepo coachRepo; 
	@Autowired UserRepo userRepo;
	
	@Override
	public List<Coach> getAllCoaches() {
		
		List<Coach> coachs = coachRepo.findAll();
		
		return coachs;
	}

	@Override
	public Coach createCoach(Coach coach) {
		
		Coach newCoach = coachRepo.save(coach);
		
		return newCoach;
	}
	
	@Override
	public Optional<Coach> findById(Integer id) {
		Optional<Coach> coach  = coachRepo.findById(id);
		return coach;
	}
	
	
	@Override
	public Optional<Coach> findByEmail(String email){
		Optional<User> user = userRepo.findByUserName(email);
		if(user.isPresent()) {
			Integer id = user.get().getId();
			Optional<Coach> coach = coachRepo.findById(id);
			return coach;
		}
		return Optional.empty();
	}

}
