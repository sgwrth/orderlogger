package dev.sgwrth.orderlogger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sgwrth.orderlogger.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
