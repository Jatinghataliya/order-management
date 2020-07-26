package com.order.item.service.exceptions;

import org.springframework.stereotype.Component;

import com.order.item.service.models.OrderItem;

@Component
public class OrderItemNotCreated extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String error;
	private OrderItem  orderItem;
	
	public OrderItemNotCreated() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItemNotCreated(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public OrderItemNotCreated(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public OrderItemNotCreated(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public OrderItemNotCreated(String error) {
		super();
		this.error = error;
	}
	
	public OrderItemNotCreated(String error, OrderItem orderItem) {
		super();
		this.error = error;
		this.orderItem = orderItem;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return error;
	}

	@Override
	public String getLocalizedMessage() {
		// TODO Auto-generated method stub
		return super.getLocalizedMessage();
	}

	public String getError() {
		return error;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}
	
}