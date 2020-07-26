package com.order.item.service.configuration;

import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.order.item.service.exceptions.OrderItemNotCreated;
import com.order.item.service.exceptions.OrderItemNotFound;
import com.order.item.service.models.Error;
import com.order.item.service.models.OrderItem;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(value = OrderItemNotFound.class)
	public ResponseEntity<Error<List<OrderItem>>> exception(OrderItemNotFound orderItemNotFound){
		return new ResponseEntity<>(Error.<List<OrderItem>>builder().code(orderItemNotFound.getErrorCode()).message(orderItemNotFound.getMessage()).data(Collections.<OrderItem>emptyList()).build(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = OrderItemNotCreated.class)
	public ResponseEntity<Error<OrderItem>> exception(OrderItemNotCreated orderItemNotCreated){
		return new ResponseEntity<>(Error.<OrderItem>builder().code(204).message(orderItemNotCreated.getMessage()).data(new OrderItem()).build(), HttpStatus.NOT_FOUND);
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