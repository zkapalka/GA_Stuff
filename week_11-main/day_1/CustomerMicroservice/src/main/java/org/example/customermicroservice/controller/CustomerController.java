package org.example.customermicroservice.controller;

import org.example.customermicroservice.model.Customer;
import org.example.customermicroservice.orderDTO.OrderDTO;
import org.example.customermicroservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }


    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable Integer customerId) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrdersByCustomer(@PathVariable Integer customerId) {
        String orderMicroserviceUrl = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/orders/customer/" + customerId)
                .queryParam("customerId", customerId)
                .toUriString();

        try {
            ResponseEntity<OrderDTO[]> responseEntity = restTemplate.getForEntity(orderMicroserviceUrl, OrderDTO[].class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                List<OrderDTO> orders = Arrays.asList(responseEntity.getBody());
                return ResponseEntity.ok().body(orders);
            } else {
                System.out.println("Empty list, sorry");
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

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

}