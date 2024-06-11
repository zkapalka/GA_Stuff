package org.example.ordermicroservice.repository;

import org.example.ordermicroservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomerId(Integer customerId);
}
