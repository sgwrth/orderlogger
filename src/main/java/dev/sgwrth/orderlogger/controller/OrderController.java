package dev.sgwrth.orderlogger.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sgwrth.orderlogger.entity.Order;
import dev.sgwrth.orderlogger.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	private OrderService orderService;
	
	OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/all")
	public List<Order> getOrders() {
		return this.orderService.getOrders();
	}
}
