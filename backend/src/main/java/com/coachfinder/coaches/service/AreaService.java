package com.coachfinder.coaches.service;

import java.util.List;

import com.coachfinder.coaches.entity.CoachArea;


public interface AreaService {

	List<CoachArea> getAllAreas();
	CoachArea createArea(CoachArea area);
	
	
	CoachArea findByName(String name);
	


}
