package dev.sgwrth.orderlogger.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sgwrth.orderlogger.entity.Customer;
import dev.sgwrth.orderlogger.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private CustomerService customerService;
	
	CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/all")
	private List<Customer> getCustomers() {
		return this.customerService.getCustomers();
	}

}
