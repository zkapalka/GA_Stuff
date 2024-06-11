package org.example.ordermicroservice.controller;

import org.example.ordermicroservice.model.Order;
import org.example.ordermicroservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getAllOrdersByCustomer(@PathVariable Integer customerId) {
        return orderService.getAllOrderByCustomer(customerId);
    }


    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        if (order.getCustomerId() == null) {
            return ResponseEntity.badRequest().body("Customer ID can't be null");
        }

        Order orderMade = orderService.addOrder(order);

        if (orderMade == null) {
            return ResponseEntity.badRequest().body("Customer not found and order not made");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(orderMade);
    }


}

