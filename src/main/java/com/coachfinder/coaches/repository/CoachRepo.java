package com.coachfinder.coaches.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coachfinder.coaches.entity.Coach;


@Repository
public interface CoachRepo extends JpaRepository<Coach, Integer> {
	
	
	public List<Coach> findAll();
	
	
	String  FACWA = "SELECT\n" + 
			"	c.coach_id ,\n" + 
			"	c.first_name ,\n" + 
			"	c.last_name ,\n" + 
			"	c.description ,\n" + 
			"	c.hourly_rate ,\n" + 
			"	GROUP_CONCAT(a.name) as areas\n" + 
			"from\n" + 
			"	coach c\n" + 
			"inner join coach_areas ca on\n" + 
			"	c.coach_id = ca.coach_id\n" + 
			"inner join area a on\n" + 
			"	ca.area_id = a.area_id\n" + 
			"GROUP BY\n" + 
			"	c.coach_id\n" + 
			"order by\n" + 
			"	c.coach_id";
	@Query(value = FACWA,nativeQuery=true)
	public List<Object[]> findAllCoachWithArea();
}
