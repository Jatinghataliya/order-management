package com.order.service.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderItem {

	private long productId;
	
	@NotNull
	@NotBlank(message = "Product code is mandatory")
	private String productCode;
	
	@NotNull
	@NotBlank(message = "Product name is mandatory")
	private String productName;
	
	private long quantity;
	
	private long orderId;
	
	@JsonIgnore
	public String getJson() {
		return new Gson().toJson(this);
	}
}