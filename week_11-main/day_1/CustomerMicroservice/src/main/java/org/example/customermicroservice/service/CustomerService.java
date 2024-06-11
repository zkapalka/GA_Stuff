package org.example.customermicroservice.service;

import jakarta.persistence.criteria.Order;
import org.example.customermicroservice.model.Customer;
import org.example.customermicroservice.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer;
    }

    public ResponseEntity<List<Order>> getAllOrdersByCustomer(Integer customerId) {
        String orderMicroserviceUrl = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/orders")
                .queryParam("customerId", customerId)
                .toUriString();

        try {
            ResponseEntity<Order[]> responseEntity = restTemplate.getForEntity(orderMicroserviceUrl, Order[].class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                List<Order> orders = Arrays.asList(responseEntity.getBody());
                return ResponseEntity.ok().body(orders);
            } else {
                // Handle non-successful HTTP status codes
                // You can throw an exception or return an empty list, depending on your requirements
                return ResponseEntity.status(responseEntity.getStatusCode()).body(Collections.emptyList());
            }
        } catch (RestClientException e) {
            // Handle RestClientException (e.g., network errors)
            // You can log the error and return an empty list or throw an exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

}
