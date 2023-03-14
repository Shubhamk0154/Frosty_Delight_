package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Role;
import com.app.pojos.User;

public interface userRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmailAndPassword(String em, String pass);

	User findByEmail(String email);

	User findByUserRoles(String string);

}
