package com.order.item.service.exceptions;

import org.springframework.stereotype.Component;

@Component
public class OrderItemNotFound extends RuntimeException {

	/**
	 * 
	 */
	
	private String error;
	private int errorCode; 
	private static final String LOCAL_ERROR_MESSAGE = "Ordered Item not found";
	
	private static final long serialVersionUID = 1L;

	public OrderItemNotFound(String error) {
		super();
		this.error = error;
	}
	
	public OrderItemNotFound(String error, int errorCode) {
		super();
		this.error = error;
		this.errorCode = errorCode;
	}
	
	public OrderItemNotFound() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItemNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public OrderItemNotFound(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public OrderItemNotFound(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return error;
	}

	@Override
	public String getLocalizedMessage() {
		// TODO Auto-generated method stub
		return LOCAL_ERROR_MESSAGE;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
}