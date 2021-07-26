package com.travel.route.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	

	@Column(name = "status")
	private String status;
	
	@Column(name = "totalAmount")
	private Double totalAmount;
	
	@JsonIgnore
	@OneToMany(mappedBy ="cart",cascade = CascadeType.ALL)
	private List<RouteBusSeat> routeBusSeats = new ArrayList<>();
	
	public Cart(String status,List<RouteBusSeat> routeBusSeats,Double totalAmount) {
	this.status=status;
	this.routeBusSeats=routeBusSeats;
	this.totalAmount = totalAmount;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

}
