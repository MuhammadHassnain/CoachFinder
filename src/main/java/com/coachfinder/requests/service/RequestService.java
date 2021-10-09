package com.coachfinder.requests.service;

import java.util.List;

import com.coachfinder.coaches.entity.Coach;
import com.coachfinder.requests.entity.Request;

public interface RequestService {
	
	
	public List<Request> getRequest(Coach coach);

	public Request saveRequest(Request request);

	
}
