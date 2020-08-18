package com.order.item.service.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.item.service.exceptions.OrderItemNotCreated;
import com.order.item.service.exceptions.OrderItemNotFound;
import com.order.item.service.models.OrderItem;
import com.order.item.service.repository.OrderItemRepository;

@Service
@Transactional
public class OrderItemService {

	@Autowired
	private OrderItemRepository itemRepository;

	public List<OrderItem> createOrderItems(List<OrderItem> orderItems) throws OrderItemNotCreated {
		List<OrderItem> items = itemRepository.saveAll(orderItems);
		if(items == null || items.size() <= 0) {
			throw new OrderItemNotCreated("Orders not created");
		}
		return items;
	}
	
	public List<OrderItem> getOrderItems() throws OrderItemNotFound {
		List<OrderItem> orderItems = itemRepository.findAll();
		if(orderItems == null || orderItems.size() <= 0) {
			throw new OrderItemNotFound("Items not available", 200);
		}
		return orderItems.stream().sorted(Comparator.comparing(OrderItem::getProductName)).collect(Collectors.toList());
	}
	
	public OrderItem getOrderItem(long productId) throws OrderItemNotFound {
		OrderItem orderItem = itemRepository.findByProductId(productId);
		
		if(orderItem == null) {
			throw new OrderItemNotFound("Order not found by " + productId, 200);
		}
		
		return orderItem;
	}
	
	public List<OrderItem> getOrderItems(long orderId) throws OrderItemNotFound{
		List<OrderItem> orderItems = itemRepository.findByOrderId(orderId);
		
		if(orderItems == null || orderItems.size() <= 0) {
			throw new OrderItemNotFound("Items not available", 200);
		}
		
		return orderItems;
	}	
}