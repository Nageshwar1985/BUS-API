package com.travel.route.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "Route")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 4, message = "Name should have atleast 4 characters")
	@Column(name = "routeNo")
	private String routeNo;
	@NotBlank
	@Size(min = 4, message = "Name should have atleast 4 characters")
	@Column(name = "routeName")
	private String routeName;

	@NotBlank
	@Column(name = "source")
	private String source;

	@NotBlank
	@Column(name = "destination")
	private String destination;

	@NotNull
	@Column(name = "fare")
	private Double fare;
	
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
	private List<Bus> buses;
	
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
	private List<RouteBusSeat> routeBusSeats;

}
