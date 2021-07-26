package com.travel.customer.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CustomerAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "account_Type")
	private String accountType;
	@NotNull
	@Column(name = "min_Balance")
	private double minimumBalance;

	@NotNull
	@Column(name = "cur_Balance")
	private double currentBalance;

	@NotBlank
	@Column(name = "bank_Name")
	private String bankName;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")

	private Customer customer;

	public CustomerAccount(String accountType, double minimumBalance, double currentBalance, String bankName) {
		this.accountType = accountType;
		this.minimumBalance = minimumBalance;
		this.currentBalance = currentBalance;
		this.bankName = bankName;
	}

}
