package com.travel.route.model;

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
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "Bus")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
		@NotBlank
	@Size(min = 4, message = "Name should have atleast 4 characters")
	@Column(name = "busNo")
	private String busNo;
		
	@NotBlank
	@Column(name = "busType")
	private String busType;
	

	@NotNull
	@Column(name = "seatCount")
	private Integer seatCount;
	
	
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "route_id", referencedColumnName = "id")
	@JsonIgnore
	private Route route;
	
	
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bus")
	private List<RouteBusSeat> routeBusSeats;
	

}
