package com.app.dto;

import java.time.LocalDate;

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
public class UpdateUserRequest {
	
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Long mobileNumber;
	private LocalDate dob;

}
