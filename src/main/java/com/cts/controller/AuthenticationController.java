package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.TokenDetails;
import com.cts.model.Users;
import com.cts.service.UserAuthenticationService;

@RestController
@RequestMapping("/authenticate")

@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AuthenticationController {

	@Autowired
	private UserAuthenticationService userAuthenticationService;

	@PostMapping("/login")
	public TokenDetails getToken(@RequestBody Users user) {

		return userAuthenticationService.validateUser(user);

	}

	@PostMapping(value = "/validate")
	public boolean validateToken(@RequestBody String token) {
		// validate the token and send status
		return userAuthenticationService.validateToken(token);
	}

	@PostMapping("/username")
	public String getUsername(@RequestBody String token) {
		try {

			String name = userAuthenticationService.extractUsername(token);

			return name;
		} catch (Exception e) {

		}
		return null;
	}

}
