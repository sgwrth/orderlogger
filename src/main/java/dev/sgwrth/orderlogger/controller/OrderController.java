package dev.sgwrth.orderlogger.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sgwrth.orderlogger.dto.PlaceOrderDto;
import dev.sgwrth.orderlogger.entity.Order;
import dev.sgwrth.orderlogger.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;
	
	OrderController(
			OrderService orderService
	) {
		this.orderService = orderService;
	}
	
	@GetMapping("/all")
	private List<Order> getOrders() {
		return this.orderService.getOrders();
	}
	
	@PostMapping(value = "/new", consumes = "application/json")
	private void saveNewOrder(@RequestBody PlaceOrderDto placeOrderDto) {
		this.orderService.saveNewOrder(placeOrderDto);
	}

}
