package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Order;
import com.app.pojos.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUser(User user);

}
