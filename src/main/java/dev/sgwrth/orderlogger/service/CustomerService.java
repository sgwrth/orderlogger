package dev.sgwrth.orderlogger.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.sgwrth.orderlogger.entity.Customer;
import dev.sgwrth.orderlogger.repository.CustomerRepository;

public interface CustomerService {
	
	@Service
	class CustomerServiceImpl implements CustomerService {
		
		private CustomerRepository customerRepository;
		
		CustomerServiceImpl(CustomerRepository customerRepository) {
			this.customerRepository = customerRepository;
		}
	
		@Override
		public List<Customer> getCustomers() {
			return this.customerRepository.findAll();
		}
		
	}
	
	List<Customer> getCustomers();

}
