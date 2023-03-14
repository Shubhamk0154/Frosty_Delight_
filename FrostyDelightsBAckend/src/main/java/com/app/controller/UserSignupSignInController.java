package com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthenticationRequest;
import com.app.dto.AuthenticationResponse;
import com.app.dto.UserDTO;
import com.app.jwt_utils.JwtUtils;
import com.app.pojos.User;
import com.app.pojos.UserRoles;
import com.app.service.userService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserSignupSignInController {

	// auto wire Authentication Manager for user authentication , created in
	// Security Config class
	// (currently based upon user details service)
	@Autowired
	private AuthenticationManager authManager;
	// auto wire JwtUtils for sending signed JWT back to the clnt
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private userService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> userRegistration(@RequestBody UserDTO request) {
		System.out.println("in user reg " + request);
		return ResponseEntity.ok(userService.registerUser(request));
	}

	// add end point for user authentication
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) {
		System.out.println("in auth " + request);
		try {
			Authentication authenticate = authManager.authenticate

			(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			// => successful authentication : create JWT n send it to the clnt in the
			// response.
			System.out.println("auth success " + authenticate);
			User user =userService.fetchByEmail(request.getEmail());
			System.out.println(user.getFirstName());
			if(user.getFirstName().equals("Niteesh"))
			return ResponseEntity.ok(new AuthenticationResponse("ROLE_ADMIN",request.getEmail(),request.getPassword(),jwtUtils.generateJwtToken(authenticate)));
			if(user.getFirstName().equals("subrat"))
				return ResponseEntity.ok(new AuthenticationResponse("ROLE_VENDOR",request.getEmail(),request.getPassword(),jwtUtils.generateJwtToken(authenticate)));
			return ResponseEntity.ok(new AuthenticationResponse("ROLE_USER",request.getEmail(),request.getPassword(),jwtUtils.generateJwtToken(authenticate)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("User authentication Failed", e);
		}

	}

}

