package com.order.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.service.models.Order;

@Repository
public interface OrderServiceRepository extends JpaRepository<Order, Long>{

	Order findByOrderId(long orderId);
	
}
