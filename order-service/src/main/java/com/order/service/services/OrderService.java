package com.order.service.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.service.exceptions.OrderNotCreated;
import com.order.service.exceptions.OrderNotFound;
import com.order.service.models.Order;
import com.order.service.models.OrderItem;
import com.order.service.models.Response;
import com.order.service.repository.OrderServiceRepository;
import com.order.service.repository.feign.client.OrderItemRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderServiceRepository orderServiceRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public Order createOrder(Order order) throws OrderNotCreated{
		
		Order order2 = orderServiceRepository.save(order);
		
		if(order2 == null || order2.getOrderId() <= 0) {
			throw new OrderNotCreated(500, "Order not created", order);
		}
		
		List<OrderItem> orderItems = order
										.getOrderItems()
										.parallelStream()
										.map(oi -> 
													OrderItem
													.builder()
														.orderId(order2.getOrderId())
														.productCode(oi.getProductCode())
														.productName(oi.getProductName())
														.quantity(oi.getQuantity())
													.build())
										.collect(Collectors.toList());
		Response<List<OrderItem>> response = orderItemRepository.createOrderIte(orderItems);
		
		if(response != null && response.getData() != null) {
			order2.setOrderItems(response.getData());
		} else {
			order2.setOrderItems(Collections.emptyList());
		}
		
		return order2;
	}

	public List<Order> getOrders() throws OrderNotFound {
		List<Order> orders = orderServiceRepository.findAll();
		
		if(orders == null || orders.size() <= 0) {
			throw new OrderNotFound("Orders not available");
		}
		
		return orders
					.parallelStream()
					.map(or -> Order
								.builder()
									.customerName(or.getCustomerName())
									.orderDate(or.getOrderDate())
									.orderId(or.getOrderId())
									.shippingAddress(or.getShippingAddress())
									.total(or.getTotal())
									.orderItems(getOrderItems(or.getOrderId()))
								.build())
					.sorted(Comparator.comparing(Order::getOrderDate))
					.collect(Collectors.toList());
	}
	
	public Order getOrder(long orderId) throws OrderNotFound {
		
		Order order = orderServiceRepository.findByOrderId(orderId);
		
		if(order == null || order.getOrderId() <= 0) {
			throw new OrderNotFound("Orders not available by order id " + orderId);
		}
		
		Response<List<OrderItem>> response = orderItemRepository.getOrderItems(order.getOrderId());
		
		if(response != null && response.getData().size() > 0) {
			order.setOrderItems(response.getData());
		} else {
			order.setOrderItems(Collections.emptyList());
		}
		
		return order;
	}
	
	private List<OrderItem> getOrderItems(long orderId){
		Response<List<OrderItem>> response = orderItemRepository.getOrderItems(orderId);
		if(response != null && response.getData().size() > 0) {
			return response.getData();
		} else {
			return Collections.emptyList();
		}
	}
}