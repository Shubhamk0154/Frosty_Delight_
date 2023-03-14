package com.app.service;

import java.util.List;

import com.app.pojos.Order;

public interface OrderService {
	
	Order saveOrder(Long custId);

	List<Order> getAllOrderByCustomer(Long custId);

	String removeOrderById(Long orderId);

}
