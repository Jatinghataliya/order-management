package com.order.service.orderservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.service.controller.OrderController;
import com.order.service.models.OrderItem;
import com.order.service.models.Response;
import com.order.service.services.OrderService;

@WebMvcTest(OrderController.class)
@ActiveProfiles("OrderServiceTest")
public class OrderServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private OrderService orderService;

	@Test
	@Order(1)
	public void addOrder() throws Exception {
		List<OrderItem> items = new ArrayList<>();
		items.add(OrderItem.builder().orderId(1).productId(1).productCode("ABC_123").productName("ABC").quantity(10).build());
		items.add(OrderItem.builder().orderId(1).productId(2).productCode("BCD_123").productName("BCD").quantity(12).build());
		items.add(OrderItem.builder().orderId(1).productId(3).productCode("EFG_123").productName("EFG").quantity(13).build());
		com.order.service.models.Order order = com.order.service.models.Order.builder().orderId(1).customerName("XYZ").orderDate("23/07/1994").orderItems(items).shippingAddress("Thangadh").total(501.23).build();
		Mockito.when(orderService.createOrder(Mockito.any(com.order.service.models.Order.class))).thenReturn(order);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
												.post("/order-service/orders") 
												.contentType(MediaType.APPLICATION_JSON_VALUE)
												.accept(MediaType.APPLICATION_JSON)
												.characterEncoding("UTF-8")
												.content(this.objectMapper.writeValueAsBytes(order));
		Response<com.order.service.models.Order> response = Response.<com.order.service.models.Order>builder().code(200).message("Ok").data(order).build();
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(response)));
	}

	
	@Test
	@Order(2)
	public void getAllOrder() throws Exception {
		List<com.order.service.models.Order> orders = new ArrayList<>();
		List<OrderItem> items = new ArrayList<>();
		items.add(OrderItem.builder().orderId(1).productId(1).productCode("ABC_123").productName("ABC").quantity(10).build());
		items.add(OrderItem.builder().orderId(1).productId(2).productCode("BCD_123").productName("BCD").quantity(12).build());
		items.add(OrderItem.builder().orderId(1).productId(3).productCode("EFG_123").productName("EFG").quantity(13).build());
		com.order.service.models.Order order = com.order.service.models.Order.builder().orderId(1).customerName("XYZ").orderDate("23/07/1994").orderItems(items).shippingAddress("Thangadh").total(501.23).build();
		orders.add(order);
		Response<List<com.order.service.models.Order>> response = Response.<List<com.order.service.models.Order>>builder().code(200).message("Ok").data(orders).build();
		Mockito.when(orderService.getOrders()).thenReturn(orders);
		mockMvc.perform(MockMvcRequestBuilders.get("/order-service/orders").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(response)));
		
	}
	
	@Test
	@Order(3)
	public void getOrderByOrderId() throws JsonProcessingException, Exception {
		List<OrderItem> items = new ArrayList<>();
		items.add(OrderItem.builder().orderId(1).productId(1).productCode("ABC_123").productName("ABC").quantity(10).build());
		items.add(OrderItem.builder().orderId(1).productId(2).productCode("BCD_123").productName("BCD").quantity(12).build());
		items.add(OrderItem.builder().orderId(1).productId(3).productCode("EFG_123").productName("EFG").quantity(13).build());
		com.order.service.models.Order order = com.order.service.models.Order.builder().orderId(1).customerName("XYZ").orderDate("23/07/1994").orderItems(items).shippingAddress("Thangadh").total(501.23).build();
		Response<com.order.service.models.Order> response = Response.<com.order.service.models.Order>builder().code(200).message("Ok").data(order).build();
		Mockito.when(orderService.getOrder(1)).thenReturn(order);
		mockMvc.perform(MockMvcRequestBuilders.get("/order-service/orders/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(response)));
	}
}