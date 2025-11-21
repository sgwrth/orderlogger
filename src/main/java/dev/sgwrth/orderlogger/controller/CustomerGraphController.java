package dev.sgwrth.orderlogger.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import dev.sgwrth.orderlogger.entity.Customer;
import dev.sgwrth.orderlogger.repository.CustomerRepository;

@Controller
public class CustomerGraphController {

	private final CustomerRepository customerRepository;

	public CustomerGraphController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@QueryMapping
	public Customer customerById(@Argument Long id) {
		return this.customerRepository.findById(id).orElseThrow();
	}

}
