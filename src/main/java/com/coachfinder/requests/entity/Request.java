package com.coachfinder.requests.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.coachfinder.coaches.entity.Coach;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request")
@Getter
@NoArgsConstructor
public class Request {
	
	@Id
	@Column(name = "REQUEST_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="COACH_ID",referencedColumnName = "COACH_ID")
	private Coach coach;
	
	@Column(name="REQUESTER_EMAIL",nullable = false,length = 50)
	private String email;
	
	@Column(name="MESSAGE",nullable = false,length = 255)
	private String message;

	public Request(Coach coach, String email, String message) {
		super();
		this.coach = coach;
		this.email = email;
		this.message = message;
	}
	
	
	
	
	
}
