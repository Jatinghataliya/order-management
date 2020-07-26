package com.order.service.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
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
@Entity (name = "orders")
@Builder
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private long orderId;
	
	@Column(name = "order_date", nullable = false)
	@NotBlank(message = "Order date is mandatory")
	@NotNull
	private String orderDate;
	
	@Column(name = "customer_name", nullable = false)
	@NotBlank(message = "Customer name is mandatory")
	@NotNull
	private String customerName;
	
	@Column(name = "shipping_address", nullable = false)
	@NotBlank(message = "Shipping address is mandatory")
	@NotNull
	private String shippingAddress;
	
	@Column(name = "total")
	private double total;
	
	@JsonIgnore
	public String getJson() {
		return new Gson().toJson(this);
	}
	
	@NotNull
	@Transient
	private List<OrderItem> orderItems;
}