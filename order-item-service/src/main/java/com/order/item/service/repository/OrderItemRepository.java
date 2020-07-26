package com.order.item.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.item.service.models.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	
	OrderItem findByProductId(long productId);

	List<OrderItem> findByOrderId(long orderId);
}
