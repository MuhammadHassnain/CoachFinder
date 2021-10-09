package com.coachfinder.coaches.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coachfinder.coaches.entity.CoachArea;
import com.coachfinder.coaches.repository.AreaRepo;


@Service
public class AreaServiceImpl implements AreaService {

	@Autowired AreaRepo areaRepo;
	
	@Override
	public List<CoachArea> getAllAreas() {
		List<CoachArea> areas =  areaRepo.findAll();
//		return areas.stream()
//				.map(area -> AreaMapper.AreaToAreaDto(area))
//				.collect(Collectors.toList());
		return areas;
	}

	@Override
	public CoachArea createArea(CoachArea area) {		
		CoachArea newArea = areaRepo.save(area);		
		return newArea;
		
	}

	@Override
	public CoachArea findByName(String name) {
		
		Optional<CoachArea> area = areaRepo.findByName(name); 
		
		if(area.isPresent()) {
			return area.get();
		}
		return null;
	}

}
