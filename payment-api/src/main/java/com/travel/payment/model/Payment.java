package com.travel.payment.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	

	@Column(name = "status")
	private String status;
	
	@Column(name = "totalAmount")
	private Double totalAmount;
	
	
	@Column(name = "updated_On")
	private Timestamp updatedOn;
	
	@Column(name = "created_On")
	private Timestamp createdOn;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_account_id", referencedColumnName = "id")
	private CustomerAccount customerAccount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vendor_account_id", referencedColumnName = "id")
	private VendorAccount vendorAccount;
	
	
	@JsonIgnore
	@OneToMany(mappedBy ="payment",cascade = CascadeType.ALL)
	private List<Transfer> transfers = new ArrayList<>();
	
//	
//	@JsonIgnore
//	@ManyToOne(mappedBy ="order",cascade = CascadeType.ALL)
//	private User user ;
	
	@OneToMany(mappedBy ="booking",cascade = CascadeType.ALL)
	private List<BookingRouteBusSeat> bookingRouteBusSeats = new ArrayList<>();
	
	@Column(name = "cartId")
	private Long cartId;                      
	
	public Payment(Long cartId,String status,Timestamp createdOn,Timestamp updatedOn,Double totalAmount) {
	this.status=status;
	this.cartId =cartId;
	this.createdOn =createdOn;
	this.updatedOn = updatedOn;
	this.totalAmount=totalAmount;
	}

}
