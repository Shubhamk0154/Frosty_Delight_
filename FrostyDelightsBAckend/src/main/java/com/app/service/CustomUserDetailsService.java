package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.pojos.User;
import com.app.repository.userRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private userRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("in load by user name"+email);
		
		User user= userRepo.findByEmail(email);//.orElseThrow(() -> new UsernameNotFoundException("Invalid Email ID"));
		
		return new CustomUserDetails(user);
	}

}
