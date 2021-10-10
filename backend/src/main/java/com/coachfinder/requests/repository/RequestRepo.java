package com.coachfinder.requests.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coachfinder.coaches.entity.Coach;
import com.coachfinder.requests.entity.Request;

@Repository
public interface RequestRepo extends JpaRepository<Request, Integer>{
	

	List<Request> findAllByCoach(Coach coach);
	

	

}
