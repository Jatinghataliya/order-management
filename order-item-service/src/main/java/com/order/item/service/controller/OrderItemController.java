package com.order.item.service.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.item.service.exceptions.OrderItemNotCreated;
import com.order.item.service.exceptions.OrderItemNotFound;
import com.order.item.service.models.OrderItem;
import com.order.item.service.models.Response;
import com.order.item.service.services.OrderItemService;

@RestController
@RequestMapping("/order-item-service")
public class OrderItemController {

	private Logger logger = LoggerFactory.getLogger(OrderItemController.class);
	
	@Autowired
	private OrderItemService orderItemService;
	
	@PostMapping("/order-items")
	public Response<List<OrderItem>> createOrderItem(@Valid @RequestBody List<OrderItem> orderItem) throws OrderItemNotCreated {
		logger.info("Create Orders");
		if(orderItem == null || orderItem.size() <= 0) {
			return Response.<List<OrderItem>>builder().code(200).message("Please provide order items data").data(orderItem).build();
		}
		return Response.<List<OrderItem>>builder().code(200).message("Ok").data(orderItemService.createOrderItems(orderItem)).build();
	}
	
	@GetMapping("/order-items/{orderitemid}")
	public Response<OrderItem> getOrderItem(@PathVariable("orderitemid") @Min(1) long orderItemId) throws OrderItemNotFound{
		logger.info("Get Order Item By :" + orderItemId);
		return Response.<OrderItem>builder().code(200).message("Ok").data(orderItemService.getOrderItem(orderItemId)).build();
	}
	
	@GetMapping("/order-items/orders/{orderid}")
	public Response<List<OrderItem>> getOrderItemByOrderId(@PathVariable("orderid") @Min(1) long orderId) throws OrderItemNotFound{
		logger.info("Get order item by order id :" + orderId);
		return Response.<List<OrderItem>>builder().code(200).message("Ok").data(orderItemService.getOrderItems(orderId)).build();
	}
	
	@GetMapping("/order-items")
	public Response<List<OrderItem>> getOrderItems() throws OrderItemNotFound {
		logger.info("Get All Order Items");
		return Response.<List<OrderItem>>builder().code(200).message("Ok").data(orderItemService.getOrderItems()).build();
	}
}