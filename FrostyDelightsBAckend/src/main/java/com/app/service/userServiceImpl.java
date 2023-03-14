package com.app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.CustomException.ResourceNotFoundException;
import com.app.dto.LoginRequestDTO;
import com.app.dto.UpdateUserRequest;
import com.app.dto.UserDTO;
import com.app.dto.UserSendDTO;
import com.app.pojos.DeliveryBoy;
import com.app.pojos.Role;
import com.app.pojos.User;
import com.app.pojos.UserRoles;
import com.app.repository.RoleRepository;
import com.app.repository.userRepository;


@Service
@Transactional
public class userServiceImpl implements userService {
	
	@Autowired
	public userRepository userRepo; 
	
	@Autowired
	public RoleRepository roleRepo; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper mapper;
	
//	@Override
//	public User addUserDetails(UserDTO transientUser) {
//		
//		
//		     String encPassword = passwordEncoder.encode(transientUser.getPassword());
//		     transientUser.setPassword(encPassword);
//		   
//		     User tempUser = mapper.map(transientUser, User.class);
//		     System.out.println(tempUser);
//		
//			return userRepo.save(tempUser);
//	}

	@Override
	public UserSendDTO registerUser(UserDTO request) {
		
		User user = new User();
		user.setEmail(request.getEmail());
		user.setDob(request.getDob());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setMobileNumber(request.getMobileNumber());
		user.setPassword(passwordEncoder.encode(request.getPassword()));//set encoded pwd
		
		Set<Role> roles = request.getRoles().stream().map(roleName -> roleRepo.findByUserRole(UserRoles.valueOf(roleName)).get())
				.collect(Collectors.toSet());
		user.setUserRoles(roles);
		
		User persistentUser = userRepo.save(user);//persisted user details in db
//		UserResponseDTO dto = new UserResponseDTO();
//		BeanUtils.copyProperties(persistentUser, dto);//for sending resp : copied User--->User resp DTO
		
		
		return mapper.map(persistentUser, UserSendDTO.class);
		
	}

	@Override
	public void deleteUserById(Long userId) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String userName = (String) authentication.getPrincipal();

		    userRepo.deleteById(userId);
		
	}

	@Override
	public User updateUserInfo(UpdateUserRequest updateUserRequest, Long id) {
		if (userRepo.existsById(id)) {
			
			User user =mapper.map(updateUserRequest, User.class);
			
			return userRepo.save(user);
		}
		throw new ResourceNotFoundException("Invalid Emp Id : Updation Failed!!!!!!!!");
	}
	
	 @Override
	    public User getUser() {
	        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
	        if (Objects.isNull(userName)) {
	            throw new AccessDeniedException("Invalid access");
	        }

	        Optional<User> user = Optional.of(userRepo.findByEmail(userName));
	        if (user.isEmpty()) {
	            throw new ResourceNotFoundException("User not found");
	        }
	        return user.get();
	    }

	@Override
	public User saveUser(User user) {
		  if (Objects.isNull(user)) {
	            throw new IllegalArgumentException("Null user");
	        }

	        return userRepo.save(user);
	}

	@Override
	public User fetchByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public List<User> fetchAllUsers() {
		return userRepo.findAll();
		//mapper.map(user, UserSendDTO);
	}

	@Override
	public User findUserById(Long custId) {
		
		return userRepo.findById(custId).orElseThrow(()-> new ResourceNotFoundException("wrong Id"));
	}

	@Override
	public User findUserByRole(String string) {
		return userRepo.findByUserRoles(string);
		
	}
		
	}

//	@Override
//	public UserSendDTO authenticateUser(LoginRequestDTO dto) {
//		
//		
//		Optional<User> user= userRepo.findByEmail(dto.getEmail());
//		System.out.println(user);
//		String rawPassword = dto.getPassword();
//		if(user!=null && passwordEncoder.matches(rawPassword, user.getPassword()))
//		return mapper.map(user, UserSendDTO.class);
//		else throw new ResourceNotFoundException("Error in email or password");
//	
//	}
	
	
	
	
		 

