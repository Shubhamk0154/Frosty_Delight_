package com.app.dto;

import javax.persistence.Column;

import com.app.pojos.Category;

import lombok.Data;

@Data
public class ProductAddDTO {
	
	private String productName;
	private double price;
	private String description;
	private boolean inStock=true;
	private Category productCategory;
	private String image;
}
