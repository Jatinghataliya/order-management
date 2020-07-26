package com.order.item.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "orderitem")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private long productId;
	
	@Column(name = "product_code", unique = true)
	@NotNull
	@NotBlank(message = "Product code is mandatory")
	private String productCode;
	
	@Column(name = "product_name")
	@NotNull
	@NotBlank(message = "Product name is mandatory")
	private String productName;
	
	@Column(name = "quantity")
	private long quantity;
	
	@Column(name = "order_id")
	private long orderId;
	
	@JsonIgnore
	public String getJson() {
		return new Gson().toJson(this);
	}
}
