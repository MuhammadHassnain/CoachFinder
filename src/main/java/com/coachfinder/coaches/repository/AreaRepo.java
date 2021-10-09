package com.coachfinder.coaches.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coachfinder.coaches.entity.CoachArea;

@Repository
public interface AreaRepo extends JpaRepository<CoachArea, Integer>{

	Optional<CoachArea> findByName(String name);
	
	
	
	

}
