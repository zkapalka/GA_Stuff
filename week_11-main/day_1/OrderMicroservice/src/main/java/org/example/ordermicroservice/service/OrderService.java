package org.example.ordermicroservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.ordermicroservice.dto.CustomerDTO;
import org.example.ordermicroservice.model.Order;
import org.example.ordermicroservice.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IOrderRepository orderRepository;
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getAllOrderByCustomer(Integer customerId){
        return orderRepository.findByCustomerId(customerId);
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order addOrder(Order order) {
        Integer customerId = order.getCustomerId();

        log.info("Customer with ID {} was passed", customerId);

        try {
            ResponseEntity<CustomerDTO> response = restTemplate.getForEntity(
                    "http://localhost:8080/customer/{customerId}",
                    CustomerDTO.class,
                    customerId);

            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("Failed to retrieve customer information for customer with ID {}", customerId);
                return null; // Or throw an exception
            }

            CustomerDTO customerDTO = response.getBody();
            if (customerDTO == null ) {
                log.error("Customer not found with ID {}", customerId);
                return null; // Or throw an exception
            }

            log.info("Customer found with ID {} and name {}", customerId, customerDTO.getName());

            return orderRepository.save(order);
        } catch (RestClientException e) {
            log.error("Error occurred while retrieving customer information for customer with ID {}: {}", customerId, e.getMessage());
            return null; // Or throw an exception
        }
    }

}
