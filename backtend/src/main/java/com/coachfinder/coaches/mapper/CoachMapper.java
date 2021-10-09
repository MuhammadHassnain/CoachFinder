package com.coachfinder.coaches.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.coachfinder.coaches.dto.CoachDTO;
import com.coachfinder.coaches.entity.Coach;
import com.coachfinder.coaches.entity.CoachArea;
import com.coachfinder.coaches.service.AreaService;
import com.coachfinder.security.auth.user.User;
import com.coachfinder.security.auth.role.Role;
import com.coachfinder.security.auth.appuser.UserRole;
import com.coachfinder.security.auth.role.RoleService;

@Component
public class CoachMapper {
	
	public static AreaService areaService;
	public static RoleService roleService;
	private static PasswordEncoder passwordEncoder;

	@Autowired
	public CoachMapper(AreaService areaService,RoleService roleService,PasswordEncoder passwordEncoder) {
		CoachMapper.areaService = areaService;
		CoachMapper.roleService = roleService;
		CoachMapper.passwordEncoder = passwordEncoder;
	}
	
	public static CoachDTO CoachToCoachDTO(Coach coach) {
		
		CoachDTO dto = new CoachDTO();
		dto.setId(coach.getId());
		dto.setFirstName(coach.getFirstName());
		dto.setLastName(coach.getLastName());
		dto.setHourlyRate(coach.getHourlyRate());
		dto.setDescription(coach.getDescription());
		
		
		List<String> areas = coach.getAreas()
								.stream()
								.map(area -> area.getName().toString())
								.collect(Collectors.toList());
		
		dto.setAreas(areas);
		return dto;

	}
	
	public static CoachDTO CoachToCoachDTO(Coach coach,String email) {
			
			CoachDTO dto = CoachMapper.CoachToCoachDTO(coach);
			dto.setEmail(email);
			return dto;
	
		}
	
	public static Coach CoachDTOtoCoach(CoachDTO coachDTO) {
		
		
	
		
		List<String> areas = coachDTO.getAreas();
		Set<CoachArea> areasSet = new HashSet<CoachArea>();
		for (String area : areas) {	
			
			CoachArea coachArea = CoachMapper.areaService.findByName(area);
			if(coachArea!=null) {
				areasSet.add(coachArea);
			}
			
		}
			Coach coach = new Coach(coachDTO.getId(),coachDTO.getFirstName()
					, coachDTO.getLastName()
					, coachDTO.getDescription()
					, coachDTO.getHourlyRate(), areasSet);
					return coach;
	
		}
	
	public static User CoachDTOtoUser(CoachDTO coach,boolean encodePassword) {
		if(encodePassword) {
			coach.setPassword(passwordEncoder.encode(coach.getPassword()));
		}
		List<String> user_roles = UserRole.COACH.getRoleWithPermissions().stream()
				.map(item -> item.getName())
				.collect(Collectors.toList());
		Set<Role> roles = CoachMapper.roleService.findByNameIn(user_roles);

		User user = new User(coach.getPassword(),coach.getEmail(),true,true,true,true,roles);
		return user;
	}

}
