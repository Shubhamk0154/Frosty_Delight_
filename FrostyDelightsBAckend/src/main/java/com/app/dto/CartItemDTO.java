package com.app.dto;

import com.app.pojos.Product;

import lombok.Data;

@Data
public class CartItemDTO {
	
	//private String productName;
	private double totalPrice;
	private Product cartProduct;
	//private int quantity;

}
