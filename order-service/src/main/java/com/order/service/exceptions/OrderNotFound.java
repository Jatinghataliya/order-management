package com.order.service.exceptions;

import org.springframework.stereotype.Component;

@Component
public class OrderNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int errorCode;
	private String errorMessage;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return errorMessage;
	}
	@Override
	public String getLocalizedMessage() {
		// TODO Auto-generated method stub
		return super.getLocalizedMessage();
	}
	public OrderNotFound() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public OrderNotFound(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public OrderNotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		this.errorMessage = message; 
	}
	public OrderNotFound(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	public OrderNotFound(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
}