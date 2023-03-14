package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;//JPA : Java EE specs

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString(exclude="userRoles,cart")
@Table(name = "users_tbl")
public class User extends BaseEntity {
	@Column(name = "first_name", length = 20)
	private String firstName;
	
	@Column(name = "last_name", length = 20)
	private String lastName;
	@Column(length = 30, unique = true) 
	private String email;
	@Column(length = 200, nullable = false)
	private String password;
	@Column(name = "mobile_number", length = 10)
	private long mobileNumber;
	private LocalDate dob;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private ShoppingCart cart;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> userRoles = new HashSet<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
			fetch=FetchType.LAZY, orphanRemoval = true)
	@JsonIgnore
	private List<Order> orders = new ArrayList<>();
	
	public void addOrderToUser(Order order) {
		this.orders.add(order);
	}

	
//	@ElementCollection 
//	@CollectionTable(name = "payment_cards", 
//	joinColumns = @JoinColumn(name = "user_id"))
//	private List<PaymentCard> paymentCards = new ArrayList<>();

	
}
