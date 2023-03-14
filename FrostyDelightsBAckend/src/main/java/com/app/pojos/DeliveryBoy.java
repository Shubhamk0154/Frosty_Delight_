package com.app.pojos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name="deliver_boy")
public class DeliveryBoy extends BaseEntity {
 
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "mobile_number")
	private long mobileNumber;
	@Column(name = "vehicle_number")
	private String vehicleNumber;
    
	@OneToMany(mappedBy = "deliveryDetails",fetch=FetchType.EAGER,orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Order> orderDetails;
	
	
}
