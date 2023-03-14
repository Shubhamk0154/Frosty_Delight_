package com.app.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartResponse {
	
	private List<CartItemDTO> cartItems;
	private double totalCartPrice;

}
