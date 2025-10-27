package dev.sgwrth.orderlogger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sgwrth.orderlogger.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
