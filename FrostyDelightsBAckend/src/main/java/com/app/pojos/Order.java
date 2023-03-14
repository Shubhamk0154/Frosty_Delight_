package com.app.pojos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity(name = "Orders")
public class Order extends BaseEntity {

	@CreationTimestamp
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	private double amount;
	
	@Column(name = "order_date")
	@CreationTimestamp
	private LocalDateTime orderDate;
	
	@Column(name = "delivery_date")
	@UpdateTimestamp
	private LocalDateTime delivery_date;
	
	@NotBlank
	private String Address;
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
	        name = "Order_Product", 
	        joinColumns = { @JoinColumn(name = "orderId") }, 
	        inverseJoinColumns = {@JoinColumn(name = "ProductId")})
	private List<Product> orderProducts = new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="del_boy_id")
	private DeliveryBoy deliveryDetails;
	
	public void addCartProductToOrder(List<Product> Product) {
		orderProducts.addAll(Product);
	}
	
//	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL, 
//			fetch = FetchType.LAZY, orphanRemoval = true)
//	private TransactionDetail transactionDetail;

	

//	public TransactionDetail getTransactionDetail() {
//		return transactionDetail;
//	}
//
//	public void setTransactionDetail(TransactionDetail transactionDetail) {
//		this.transactionDetail = transactionDetail;
//	}

	
}