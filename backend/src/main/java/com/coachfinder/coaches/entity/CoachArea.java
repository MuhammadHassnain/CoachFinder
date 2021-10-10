package com.coachfinder.coaches.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "area")
@Getter
@NoArgsConstructor
public class CoachArea {
	
	
	@Id
	@Column(name = "area_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	
	@Column(name = "NAME" ,nullable=false, unique = true)
	private String name;


	public CoachArea(String name) {
		this.name = name;
	}
	
	
	
	

}
