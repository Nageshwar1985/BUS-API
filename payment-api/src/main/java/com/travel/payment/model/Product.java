package com.travel.payment.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "product")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(min = 4, message = "Name should have atleast 4 characters")
	@Column(name = "name")
	private String name;
	@NotBlank
	@Size(min = 4, message = "Name should have atleast 4 characters")
	@Column(name = "description")
	private String desc;

	@NotNull
	@Column(name = "quantity")
	private Integer quantity;


	@Column(name = "category")
	private String category;
	@NotNull
	@Column(name = "vendorName")
	private String vendorName;

	@Column(name = "status")
	private String status;
	
//	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	private Cart cart;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vendor_id", referencedColumnName = "id")
	private Vendor vendor;

}
