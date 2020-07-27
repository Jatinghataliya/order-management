package com.order.item.service.cotroller;

import java.util.ArrayList;
import java.util.Collections;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.item.service.controller.OrderItemController;
import com.order.item.service.models.OrderItem;
import com.order.item.service.models.Response;
import com.order.item.service.services.OrderItemService;


@WebMvcTest(OrderItemController.class)
@ActiveProfiles("OrderItemTest")
public class OrderItemTestController {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private OrderItemService orderItemService;

	@Test
	@Order(1)
	public void addOrderItems() throws Exception {
		List<OrderItem> items = new ArrayList<>();
		items.add(OrderItem.builder().orderId(1).productId(1).productCode("ABC_123").productName("ABC").quantity(10).build());
		items.add(OrderItem.builder().orderId(1).productId(2).productCode("BCD_123").productName("BCD").quantity(12).build());
		items.add(OrderItem.builder().orderId(1).productId(3).productCode("EFG_123").productName("EFG").quantity(13).build());
		Mockito.when(orderItemService.createOrderItems(Mockito.anyList())).thenReturn(items);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
												.post("/order-item-service/order-items") 
												.contentType(MediaType.APPLICATION_JSON_VALUE)
												.accept(MediaType.APPLICATION_JSON)
												.characterEncoding("UTF-8")
												.content(this.objectMapper.writeValueAsBytes(items));
		Response<List<OrderItem>> response = Response.<List<OrderItem>>builder().code(200).message("Ok").data(items).build();
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(response)));
	}
	
	@Test
	@Order(2)
	public void getAllOrderItems() throws Exception {
		List<OrderItem> items = new ArrayList<>();
		items.add(OrderItem.builder().orderId(1).productId(1).productCode("ABC_123").productName("ABC").quantity(10).build());
		items.add(OrderItem.builder().orderId(1).productId(2).productCode("BCD_123").productName("BCD").quantity(12).build());
		items.add(OrderItem.builder().orderId(1).productId(3).productCode("EFG_123").productName("EFG").quantity(13).build());
		Response<List<OrderItem>> response = Response.<List<OrderItem>>builder().code(200).message("Ok").data(items).build();
		Mockito.when(orderItemService.getOrderItems()).thenReturn(items);
		mockMvc.perform(MockMvcRequestBuilders.get("/order-item-service/order-items").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(response)));
	}
	
	@Test
	@Order(3)
	public void getNullOrderItemsByOrderId() throws Exception {
		Response<List<OrderItem>> response = Response.<List<OrderItem>>builder().code(200).message("Ok").data(Collections.emptyList()).build();
		Mockito.when(orderItemService.getOrderItems()).thenReturn(Collections.emptyList());
		mockMvc.perform(MockMvcRequestBuilders.get("/order-item-service/order-items/orders/-1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(response)));
	}
	
	@Test
	@Order(3)
	public void getNullOrderItemsByOrderItemId() throws Exception {
		Response<OrderItem> response = Response.<OrderItem>builder().code(200).message("Ok").data(null).build();
		Mockito.when(orderItemService.getOrderItems()).thenReturn(Collections.emptyList());
		mockMvc.perform(MockMvcRequestBuilders.get("/order-item-service/order-items/-1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(response)));
	}
	
}