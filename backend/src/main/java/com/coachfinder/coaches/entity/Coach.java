package com.coachfinder.coaches.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coach")
@Getter
@NoArgsConstructor
public class Coach {
	
	@Id
	@Column(name = "COACH_ID",nullable =false,unique = true)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	
	@Column(name = "FIRST_NAME", nullable=false)
	private String firstName;
	
	@Column(name = "LAST_NAME"  ,nullable=false)
	private String lastName;
	
	@Column(name = "DESCRIPTION" ,nullable=false)
	private String description;
	
	@Column(name = "HOURLY_RATE" ,nullable=false)
	private float hourlyRate ;
	

	@ManyToMany(cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
	@JoinTable(name = "coach_areas", joinColumns = @JoinColumn(name="COACH_ID")
	,inverseJoinColumns = @JoinColumn(name="AREA_ID"))
	@Column(name = "AREAS")
	private Set<CoachArea> areas = new HashSet<CoachArea>();


	public Coach(Integer id, String firstName, String lastName, String description, float hourlyRate, Set<CoachArea> areas) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.hourlyRate = hourlyRate;
		this.areas = areas;
	}


	@Override
	public String toString() {
		return "Coach [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", description="
				+ description + ", hourlyRate=" + hourlyRate + ", areas=" + areas + "]";
	}
	
	
	
	
	
	
	
	
}
