package com.app.pojos;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "products")
public class Product extends BaseEntity {
	@Column(name = "product_name", length = 30, unique = true)
	private String productName;
	private double price;
	private String description;
	@Column(name = "in_stock")
	private boolean inStock;
	//@NotBlank(message = "url cannot be blank")
	@Column(length = 300)
	private String image;
	
	@JsonBackReference
	// many to one Product *-----> 1Category
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category productCategory;
	
	
     public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String productName, double price, String description) {
		super();
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.inStock = true;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public Category getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(Category productCategory) {
		this.productCategory = productCategory;
	}
	
	@Override
	public String toString() {
		return "Product ID " + getId() + " [productName=" + productName + ", price=" + price + ", description="
				+ description + ", inStock=" + inStock + "]";
	}

	
	
}
