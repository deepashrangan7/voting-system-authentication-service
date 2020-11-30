package com.cts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cts.model.TokenDetails;
import com.cts.model.Users;
import com.cts.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserAuthenticationService {

	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LoadUsernameService loadUsernameService;

	public TokenDetails validateUser(Users user) {
		TokenDetails tokenDetails = null;

		if (user != null) {

			log.info("user with emailID {} is trying to login", user.getEmail());

			if (user.getPassword() != null && user.getEmail() != null) {

				Users fetchedUser = userRepository.findByEmailAndPasswordAndActive(user.getEmail(), user.getPassword(),1);
				
				if (fetchedUser != null) {
					Integer admin=0;
					if(fetchedUser.getRole()==2)
						admin=1;
					tokenDetails = new TokenDetails(fetchedUser.getUserId(), fetchedUser.getName(),
							jwtService.generateToken(fetchedUser.getName()),admin);
				} else {
					log.info("username and password doesn't match ");
				}

			} else {
				log.error("null value deducted name={}  password={} ", user.getName(), user.getPassword());
			}
		}

		return tokenDetails;
	}

	public String getRole(Integer roleId) {
		String role = "user";
		if (roleId == 0)
			role = "user";
		else if (roleId == 1)
			role = "candidate";
		else if (roleId == 2)
			role = "admin";
		return role;
	}

	public boolean validateToken(String token) {
		
		if (token != null && token.startsWith("Bearer ")) {

			token = token.substring(7).toString();
			log.info("token {}",token);
			String username = jwtService.extractUsername(token);
		
			log.info("user name {}",username);
			
			if (username != null) {
				UserDetails userDetails = loadUsernameService.loadUserByUsername(username);
				if (userDetails != null)
					return jwtService.validateToken(token, userDetails);
			}
		}
		return false;

	}

	public String extractUsername(String token) {

		try {
			return jwtService.extractUsername(token);
		} catch (Exception e) {
			log.error("error while fetching the username {}",e.getMessage());
		}
		return null;
	}

}
