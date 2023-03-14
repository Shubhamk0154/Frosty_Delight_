package com.app.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Long mobileNumber;
	private LocalDate dob;
	private Set<String> roles;
	

}
