package com.coachfinder.coaches;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coachfinder.coaches.dto.CoachDTO;
import com.coachfinder.coaches.entity.Coach;
import com.coachfinder.coaches.mapper.CoachMapper;
import com.coachfinder.coaches.service.CoachService;
import com.coachfinder.requests.dto.RequestDTO;
import com.coachfinder.requests.entity.Request;
import com.coachfinder.requests.mapper.RequestMapper;
import com.coachfinder.requests.service.RequestService;
import com.coachfinder.security.auth.appuser.UserRole;
import com.coachfinder.security.auth.role.Role;
import com.coachfinder.security.auth.role.RoleService;
import com.coachfinder.security.auth.user.User;
import com.coachfinder.security.auth.user.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/coaches")
public class CoachController {

	@Autowired CoachService coachService;
	
	@Autowired RequestService requestService;
	
	@Autowired UserService userService; 
	@Autowired RoleService roleService;
	
	
	@GetMapping
	public ResponseEntity<Object> getCoaches(){
		List<Coach> coaches = coachService.getAllCoaches();
		
		List<CoachDTO> coachesDto = coaches.stream()
				.map(coach -> CoachMapper.CoachToCoachDTO(coach))
				.collect(Collectors.toList());
		return new ResponseEntity<Object>(coachesDto, HttpStatus.OK);
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<Object> registerAsACoach(@RequestBody CoachDTO coachDTO){
		Authentication currUser =  SecurityContextHolder.getContext().getAuthentication();
		
		User user = userService.findUserByEmail(currUser.getName());
		
		
		
		
		if(user != null){
			//user already registered update user role 
			List<String> user_roles = UserRole.COACH.getRoleWithPermissions().stream()
			.map(item -> item.getName())
			.collect(Collectors.toList());
			Set<Role> roles = roleService.findByNameIn(user_roles);
			
			user.setRoles(roles);

			user = userService.updateUser(user);
	
			
			coachDTO.setId(user.getId());
			Coach coach = CoachMapper.CoachDTOtoCoach(coachDTO);
			
			coach =  coachService.createCoach(coach);
			CoachDTO retCoachDTO = CoachMapper.CoachToCoachDTO(coach,coachDTO.getEmail());
			return new ResponseEntity<Object>(retCoachDTO ,HttpStatus.OK);
			
		}
		return new ResponseEntity<Object>(null ,HttpStatus.EXPECTATION_FAILED);
	}
	
	/*
	 * @PostMapping public ResponseEntity<Object> registerCoach(@RequestBody
	 * CoachDTO coachDTO){
	 * 
	 * 
	 * User user = CoachMapper.CoachDTOtoUser(coachDTO,true); User newUser = null;
	 * try { newUser = userService.createUser(user); } catch( DuplicateKeyException
	 * e){ return new ResponseEntity<Object>(null ,HttpStatus.EXPECTATION_FAILED); }
	 * if(newUser!=null) { coachDTO.setId(newUser.getId()); Coach coach =
	 * CoachMapper.CoachDTOtoCoach(coachDTO);
	 * 
	 * coach = coachService.createCoach(coach); CoachDTO retCoachDTO =
	 * CoachMapper.CoachToCoachDTO(coach);
	 * retCoachDTO.setEmail(coachDTO.getEmail()); return new
	 * ResponseEntity<Object>(retCoachDTO ,HttpStatus.OK);
	 * 
	 * }
	 * 
	 * return new ResponseEntity<Object>(null ,HttpStatus.EXPECTATION_FAILED); }
	 */
	
	
	@GetMapping("/profile")
	public ResponseEntity<Object> GetCoachDetail(){
		Authentication currUser =  SecurityContextHolder.getContext().getAuthentication();
		Optional<Coach> oCoach = coachService.findByEmail(currUser.getName());
		if(oCoach.isPresent()) {
			CoachDTO dto = CoachMapper.CoachToCoachDTO(oCoach.get(),currUser.getName());
			return new ResponseEntity<Object>(dto,HttpStatus.OK);
		}
		return new ResponseEntity<Object>(null,HttpStatus.UNAUTHORIZED);
	}
	
	
	@GetMapping("/requests")
	public ResponseEntity< Object> GetCoachRequests(){
		Authentication currUser =  SecurityContextHolder.getContext().getAuthentication();
		Optional<Coach> oCoach = coachService.findByEmail(currUser.getName());
		List<Request> requests = new ArrayList<Request>();
		if(oCoach.isPresent()) {
			requests = requestService.getRequest(oCoach.get());
			List<RequestDTO> dto = requests.stream().map(request -> RequestMapper.RequestToRequestDTO(request,false)).collect(Collectors.toList());
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>(null, HttpStatus.UNAUTHORIZED);
		
		

	}
	
}
