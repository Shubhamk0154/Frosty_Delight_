package com.app.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.app.pojos.Order;
import com.app.service.OrderService;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrderService ordersService;
	
	@Autowired
	private ModelMapper mapper;
	
	public OrdersController()
	{
		System.out.println("In Ctor Of : "+getClass());
	}
	
	
	
	@PostMapping("/{custId}/cart/{cartId}")
	public ResponseEntity<?> processOrder(@PathVariable Long custId)
	{
		return ResponseEntity.ok(ordersService.saveOrder(custId));
	}
	
	@GetMapping("/{custId}")
	public ResponseEntity<?> getAllOrders(@PathVariable Long custId)
	{
		return ResponseEntity.ok(ordersService.getAllOrderByCustomer(custId));
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<?> removeOrder(@PathVariable Long orderId)
	{
		return ResponseEntity.ok(ordersService.removeOrderById(orderId));
	}
	
	
	
	

	
}