package com.order.service.exceptions;

import org.springframework.stereotype.Component;

import com.order.service.models.Order;

@Component
public class OrderNotCreated extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int errorCode;
	private String message;
	private Order order;
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	@Override
	public String getLocalizedMessage() {
		// TODO Auto-generated method stub
		return super.getLocalizedMessage();
	}
	public OrderNotCreated(int errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public OrderNotCreated(int errorCode, String message, Order order) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.order = order;
	}
	public int getErrorCode() {
		return errorCode;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Order getOrder() {
		return order;
	}
	public OrderNotCreated() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderNotCreated(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public OrderNotCreated(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public OrderNotCreated(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public OrderNotCreated(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
