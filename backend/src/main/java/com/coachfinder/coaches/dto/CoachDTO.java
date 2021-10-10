package com.coachfinder.coaches.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@ToString
public class CoachDTO {
	
	private Integer id; 
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String description;
	private float hourlyRate ;
	private List<String> areas = new ArrayList<String>();
}
