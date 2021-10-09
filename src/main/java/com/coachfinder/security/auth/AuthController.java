package com.coachfinder.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coachfinder.security.auth.user.User;
import com.coachfinder.security.auth.user.UserDto;
import com.coachfinder.security.auth.user.UserMapper;
import com.coachfinder.security.auth.user.UserService;
import com.coachfinder.utils.dto.response.Response;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
	
	@Autowired UserService userService;

	@PostMapping(value = "/signup",consumes = "application/json")
	ResponseEntity<Object> SignUp(@RequestBody AuthRequest.CreateUserRequest  request){
		
		
		User user = UserMapper.authToUser(request);
		
		try {
			user = userService.createUser(user);
			UserDto dto = UserMapper.toUserDto(user,true,false);
			Response<Object> response = Response.ok().setPayload(dto);
			return new ResponseEntity<Object>(response,HttpStatus.OK);

		}catch(DuplicateKeyException e) {
			Response<Object> response = Response.duplicateEntity();
			response.addErrorMsgToResponse("Duplicate Email Address", e);
			return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
