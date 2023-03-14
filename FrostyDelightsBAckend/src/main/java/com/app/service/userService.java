package com.app.service;

import java.util.List;

import com.app.dto.LoginRequestDTO;
import com.app.dto.UpdateUserRequest;
import com.app.dto.UserDTO;
import com.app.dto.UserSendDTO;
import com.app.pojos.DeliveryBoy;
import com.app.pojos.User;

public interface userService {

	// User addUserDetails(UserDTO transientUser);

	UserSendDTO registerUser(UserDTO request);
  
	void deleteUserById(Long userId);
	 
	User updateUserInfo(UpdateUserRequest updateUserRequest,Long id);
	//UserSendDTO authenticateUser(LoginRequestDTO dto);

	User getUser();

	User saveUser(User user);
	
	User fetchByEmail(String email);

	List<User> fetchAllUsers();

	User findUserById(Long custId);

	User findUserByRole(String string);

}
