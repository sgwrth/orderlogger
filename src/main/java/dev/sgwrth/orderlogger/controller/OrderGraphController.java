package dev.sgwrth.orderlogger.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import dev.sgwrth.orderlogger.entity.Order;
import dev.sgwrth.orderlogger.repository.OrderRepository;

@Controller
public class OrderGraphController {

	private final OrderRepository orderRepository;

	public OrderGraphController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@QueryMapping
	public Order orderById(@Argument Long id) {
		return this.orderRepository.findById(id).orElseThrow();
	}

}
