package com.coachfinder.requests.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coachfinder.coaches.entity.Coach;
import com.coachfinder.requests.entity.Request;
import com.coachfinder.requests.repository.RequestRepo;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired RequestRepo requestRepo;
	
	@Override
	public List<Request> getRequest(Coach coach) {
		
		return requestRepo.findAllByCoach(coach);

	}

	@Override
	public Request saveRequest(Request request) {
		return requestRepo.save(request);
	}

}
