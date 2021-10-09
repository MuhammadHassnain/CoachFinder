package com.coachfinder;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.coachfinder.coaches.dto.AreaDTO;
import com.coachfinder.coaches.entity.Coach;
import com.coachfinder.coaches.entity.CoachArea;
import com.coachfinder.coaches.service.AreaService;
import com.coachfinder.coaches.service.CoachService;
import com.coachfinder.requests.entity.Request;
import com.coachfinder.requests.service.RequestService;
import com.coachfinder.security.auth.appuser.UserRole;
import com.coachfinder.security.auth.role.RoleService;
import com.coachfinder.security.auth.user.UserService;
import com.coachfinder.security.auth.appuser.UserRole;
import com.coachfinder.security.auth.role.Role;

@Component
public class RunAfterStartup {
	
	
	private final AreaService areaService;
	private final CoachService coachService;
	private final RequestService requestService;
	
	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final RoleService roleService;
	
	

	@Autowired
	public RunAfterStartup(AreaService areaService
						,CoachService coachService
						,RequestService requestService
						,UserService userService,
						PasswordEncoder passwordEncoder,
						RoleService roleService) {
		super();
		this.areaService = areaService;
		this.coachService = coachService;
		this.requestService = requestService;
		
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.roleService = roleService;
	}




	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		
		
		for (UserRole role : EnumSet.allOf(UserRole.class)) {
			Set<Role> roles = role.getRoleWithPermissions();
			roleService.CreateRoles(roles);
		}
		
		
		  CoachArea frontend = areaService.createArea(new CoachArea("frontend"));
		  CoachArea backend = areaService.createArea(new CoachArea("backend"));
		  CoachArea career = areaService.createArea(new CoachArea("career"));
		  
		 /* 
		 * Set<CoachArea> fullStack = new HashSet<CoachArea>(); fullStack.add(frontend);
		 * fullStack.add(backend);
		 * 
		 * 
		 * 
		 * Coach c1 = new Coach(1, "Muhammad","hassnain", "Full Stack Developer", 30,
		 * fullStack); c1 = coachService.createCoach(c1);
		 * 
		 * 
		 * Set<CoachArea> counsil = new HashSet<CoachArea>(); counsil.add(frontend);
		 * counsil.add(backend); counsil.add(career);
		 *
		
		
		c1 = new Coach(2, "Jahanzaib","Manzoor", "Career Counsiler", 60,  
				counsil);
		c1 = coachService.createCoach(c1);
		
		System.out.println("data"+ coachService.getAllCoaches().get(0).toString());
		
		
		Request request = new Request(c1,"HassnainKadhar@gmail.com", "I need Java Development Training");
		Request request1 = new Request(c1,"HassnainKadhar@gmail.com", "I need Vue Development Training");
		Request request2 = new Request(c1,"HassnainKadhar@gmail.com", "I need CSS Training");

		requestService.saveRequest(request);
		requestService.saveRequest(request1);
		requestService.saveRequest(request2);

		
		*/
		
	}
}
