package dev.sgwrth.orderlogger.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.sgwrth.orderlogger.entity.Order;
import dev.sgwrth.orderlogger.repository.OrderRepository;

public interface OrderService {

	@Service
	class OrderServiceImpl implements OrderService {
		
		private OrderRepository orderRepository;
		
		OrderServiceImpl(OrderRepository orderRepository) {
			this.orderRepository = orderRepository;
		}
		
		@Override
		public List<Order> getOrders() {
			return this.orderRepository.findAll();
		}

	}
	
	List<Order> getOrders();
}
