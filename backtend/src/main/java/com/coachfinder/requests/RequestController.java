package com.coachfinder.requests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coachfinder.coaches.entity.Coach;
import com.coachfinder.coaches.service.CoachService;
import com.coachfinder.requests.dto.RequestDTO;
import com.coachfinder.requests.entity.Request;
import com.coachfinder.requests.mapper.RequestMapper;
import com.coachfinder.requests.service.RequestService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/requests")
public class RequestController {

	@Autowired private RequestService requestService;
	@Autowired private CoachService coachService;
	

	@GetMapping(value="/{id}")
	public ResponseEntity<Object> getCoachRequest(@PathVariable("id") Integer id){
		
		Optional<Coach> coach = coachService.findById(id);
		List<Request> requests = new ArrayList<Request>();
		if(coach.isPresent()) {
			requests = requestService.getRequest(coach.get());
		}
		List<RequestDTO> dto = requests.stream().map(request -> RequestMapper.RequestToRequestDTO(request,false)).collect(Collectors.toList());
		return new ResponseEntity<Object>(dto, HttpStatus.OK);

	}
	
	@PostMapping
	public ResponseEntity<Object> saveRequest(@RequestBody RequestDTO dto){
		Optional<Coach> coach = coachService.findById(dto.getCoachId());
		
		
		if(coach.isPresent()) {
			Request request = RequestMapper.RequestDTOtoRequest(dto, coach.get());
			Request saveRequest = requestService.saveRequest(request);
			return new ResponseEntity<Object>(RequestMapper.RequestToRequestDTO(saveRequest, true),HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Coach with given id Does not Exist",HttpStatus.BAD_REQUEST);
	}
	
}
