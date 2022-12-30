package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.converter.OrderConverter;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.service.impl.OrderServiceImpl;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderServiceImpl orderService;

	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
		OrderDto orderDto=orderService.getOrderById(id);
		return OrderConverter.dtoToResponse(orderDto);
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		OrderDto orderDto=orderService.createOrder(OrderConverter.requestTodto(order));
		return OrderConverter.dtoToResponse(orderDto);
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto orderDto=orderService.updateOrderDetails(id,OrderConverter.requestTodto(order));
		return OrderConverter.dtoToResponse(orderDto);
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		OperationStatusModel operationStatusModel=new OperationStatusModel();
		orderService.deleteOrder(id);
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDto> orderDtoList=orderService.getOrders();
		List<OrderDetailsResponse> orderDetailsResponses=new ArrayList<>();
		for(OrderDto orderDto:orderDtoList)
			orderDetailsResponses.add(OrderConverter.dtoToResponse(orderDto));
		return orderDetailsResponses;
	}
}
