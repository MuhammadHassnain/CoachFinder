package com.coachfinder.requests.dto;


import com.coachfinder.coaches.dto.CoachDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(Include.NON_NULL)
public class RequestDTO {
	
	private Integer id;
	private String email;
	private String message;
	private CoachDTO coach;
	private Integer coachId; 
	
	
	
	
}
