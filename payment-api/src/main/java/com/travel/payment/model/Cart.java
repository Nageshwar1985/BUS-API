package com.travel.payment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@JsonIgnore
	@OneToMany(mappedBy ="cart",cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<>();
	public Cart(String status,List<Product> products) {
	this.status=status;
	this.products=products;
	}

}
