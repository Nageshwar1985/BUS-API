package com.travel.bus.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyProductRequestDTO {

	@NotNull
	private LoginDTO loginDTO;
	@NotNull
	private List<Long> productIds;

}
