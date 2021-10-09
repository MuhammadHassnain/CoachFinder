package com.coachfinder.security.auth.appuser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coachfinder.security.auth.user.User;
import com.coachfinder.security.auth.user.UserDto;
import com.coachfinder.security.auth.user.UserService;

@Service
public class AppUserService implements UserDetailsService {

	@Autowired
	UserService userservice;

	private static final Logger log = LoggerFactory.getLogger(AppUserService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		log.debug("LoadUserByUsername()");

//		Optional<User> user = userservice.findUserByName(username);

		User user = userservice.findUserByEmail(username);

		if (user != null) {

			log.debug("User is present:" + user.getEmail());

			AppUser appUser = ApplicationUserBuilder.builder().username(user.getEmail())
					.password(user.getPassword()).isAccountNonExpired(user.isAccountNonExpired())
					.isAccountNonLocked(user.isAccountNonLocked())
					.isCredentialsNonExpired(user.isCredentialsNonExpired()).isEnabled(user.isEnabled())
					.authorities(user.getRoles()).build();

			return appUser;

		} else {
			log.debug("User " + username + " not found");
			throw new UsernameNotFoundException(username);
		}

	}

}
