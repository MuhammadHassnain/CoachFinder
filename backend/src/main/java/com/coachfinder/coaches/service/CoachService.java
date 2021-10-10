package com.coachfinder.coaches.service;

import java.util.List;
import java.util.Optional;

import com.coachfinder.coaches.entity.Coach;

public interface CoachService {
	
	
	List<Coach> getAllCoaches();
	
	
	Coach createCoach(Coach coach);
	
	Optional<Coach> findById(Integer id);
	
	Optional<Coach> findByEmail(String email);
	
	
}
