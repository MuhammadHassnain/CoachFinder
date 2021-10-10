package com.coachfinder.requests.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.coachfinder.coaches.entity.Coach;
import com.coachfinder.coaches.mapper.CoachMapper;
import com.coachfinder.coaches.service.CoachService;
import com.coachfinder.requests.dto.RequestDTO;
import com.coachfinder.requests.entity.Request;

public class RequestMapper {
	
	public static CoachService coachService;

	
	@Autowired
	public RequestMapper(CoachService coachService) {
		RequestMapper.coachService = coachService;
	}
	
	public static RequestDTO RequestToRequestDTO(Request request,boolean includeCoach) {
		
		RequestDTO dto = new RequestDTO();
		dto.setId(request.getId());
		dto.setEmail(request.getEmail());
		dto.setMessage(request.getMessage());
		if(includeCoach){
			dto.setCoach(CoachMapper.CoachToCoachDTO(request.getCoach()));
		}else {
			dto.setCoach(null/* CoachMapper.CoachToCoachDTO(request.getCoach()) */);
		}
		return dto;
		
		
	}
	
	public static Request RequestDTOtoRequest(RequestDTO dto) {
		
		Request request  = new Request(CoachMapper.CoachDTOtoCoach(dto.getCoach()), dto.getEmail(), dto.getMessage());
		
		return request;
	}
	
	public static Request RequestDTOtoRequest(RequestDTO dto,Coach coach) {	
		Request request  = new Request(coach, dto.getEmail(), dto.getMessage());
		return request;
	}
	
}
