package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.User;
import com.app.dto.*;

import com.app.service.userService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")

public class UserController {
	
	public UserController() {
		
	}

	@Autowired
	public userService users;
	
	@GetMapping
	public ResponseEntity<?> getAllUsers(){
		return ResponseEntity.ok(users.fetchAllUsers()); 
	}
	
	@DeleteMapping("{userId}")
	  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	  public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long userId){
	    users.deleteUserById(userId);
	    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	  }
	
	@PutMapping("/userInfo/{id}")
	 public ResponseEntity<?> updateUserInfo(@RequestBody UpdateUserRequest updateUserRequest,@PathVariable("id") Long id) {
	   users.updateUserInfo(updateUserRequest,id);
	  return new ResponseEntity<>(HttpStatus.OK);
	 
	}
	}
