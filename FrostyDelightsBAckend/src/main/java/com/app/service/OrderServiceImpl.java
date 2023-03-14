package com.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.Order;
import com.app.pojos.ShoppingCart;
import com.app.pojos.User;
import com.app.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private userService users;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ShoppingCartService cartService;
	
	

	@Override
	public Order saveOrder(Long custId) {
		User user = users.findUserById(custId);
		ShoppingCart cart =user.getCart();
		
		Order order = new Order();
		order.setAddress("Pune");
		order.setAmount(cart.getTotalCartPrice());
		order.setOrderDate(LocalDateTime.now());
		//order.setDeliveryDetails(users.findUserByRole("ROLE_DELIVERY"));
		order.setUser(user);
		orderRepo.save(order);
		cartService.emptyCart();
		return order;
		
		
		
		
		
		
	}

	@Override
	public List<Order> getAllOrderByCustomer(Long custId) {
		User user = users.findUserById(custId);
		return orderRepo.findByUser(user);
	}

	@Override
	public String removeOrderById(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
