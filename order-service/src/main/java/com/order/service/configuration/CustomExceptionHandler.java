package com.order.service.configuration;

import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.order.service.exceptions.OrderNotCreated;
import com.order.service.exceptions.OrderNotFound;
import com.order.service.models.Order;
import com.order.service.models.Error;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(value = OrderNotCreated.class)
	public ResponseEntity<Error<List<Order>>> exception(OrderNotCreated orderNotCreated){
		return new ResponseEntity<>(Error.<List<Order>>builder().code(orderNotCreated.getErrorCode()).message(orderNotCreated.getMessage()).data(Collections.<Order>emptyList()).build(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = OrderNotFound.class)
	public ResponseEntity<Error<String>> exception(OrderNotFound orderNotFound){
		return new ResponseEntity<>(Error.<String>builder().code(204).message(orderNotFound.getMessage()).data(orderNotFound.getMessage()).build(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Error<String>> exception(Exception exception) {
		return new ResponseEntity<>(Error.<String>builder().code(500).message(exception.getMessage()).data(exception.getMessage()).build(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Error<String>> exception(ConstraintViolationException constraintViolationException){
		return new ResponseEntity<>(Error.<String>builder().code(400).message(constraintViolationException.getMessage()).data(constraintViolationException.getMessage()).build(), HttpStatus.NOT_FOUND);
	}
}
