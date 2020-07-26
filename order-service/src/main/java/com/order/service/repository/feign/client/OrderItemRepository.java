package com.order.service.repository.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.order.service.models.OrderItem;
import com.order.service.models.Response;

@FeignClient(name = "order-item-service", url = "http://localhost:8282/order-item-service/")
public interface OrderItemRepository {

	@RequestMapping(value = "/order-items", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	Response<List<OrderItem>> createOrderIte(@RequestBody List<OrderItem> orderItem);
	
	@RequestMapping(value = "/order-items", method = RequestMethod.GET, produces = "application/json")
	Response<List<OrderItem>> getOrderItems();
	
	@RequestMapping(value = "/order-items/{orderitemid}", method = RequestMethod.GET, produces = "application/json")
	Response<OrderItem> getOrderItem(@PathVariable("orderitemid") long orderItemId);
	
	@RequestMapping(value = "/order-items/orders/{orderid}", method = RequestMethod.GET, produces = "application/json")
	Response<List<OrderItem>> getOrderItems(@PathVariable("orderid") long orderId);
	
}