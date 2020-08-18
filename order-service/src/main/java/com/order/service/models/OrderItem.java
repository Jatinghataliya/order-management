package com.order.service.models;

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
@Entity(name = "order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	
	@NotNull
	@NotBlank(message = "Product code is mandatory")
	@Column(name = "product_code")
	private String productCode;
	
	@NotNull
	@NotBlank(message = "Product name is mandatory")
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "quality")
	private long quantity;
	
	@Column(name = "order_id")
	private long orderId;
	
	@JsonIgnore
	public String getJson() {
		return new Gson().toJson(this);
	}
}