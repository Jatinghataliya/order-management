package com.order.service.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.models.Order;
import com.order.service.models.OrderItem;
import com.order.service.models.Response;
import com.order.service.services.OrderService;

@RestController
@RequestMapping("/order-service")
public class OrderController {

	private Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/orders")
	public Response<Order> createOrdere(@Valid @RequestBody Order order) {
		
		logger.info("Create Order : " + order.getJson());
		List<OrderItem> orderItems = order.getOrderItems();
		if(orderItems == null || orderItems.size() <= 0) {
			return Response.<Order>builder().code(400).message("Order item not found in this order").data(order).build();
		}

		return Response.<Order>builder().code(200).message("Ok").data(orderService.createOrder(order)).build();
	} 
	
	@GetMapping("/orders/{orderid}")
	public Response<Order> getOrder(@PathVariable("orderid") long orderId) {
		logger.info("Get Order By : " + orderId);
		if(orderId <= 0) {
			return Response.<Order>builder().code(400).message("Order item not found in this order").data(null).build();
		}
		return Response.<Order>builder().code(200).message("Ok").data(orderService.getOrder(orderId)).build();
	}
	
	@GetMapping("/orders")
	public Response<List<Order>> getOrders(){
		logger.info("Get Orders");
		return Response.<List<Order>>builder().code(200).message("Ok").data(orderService.getOrders()).build();
	}
}