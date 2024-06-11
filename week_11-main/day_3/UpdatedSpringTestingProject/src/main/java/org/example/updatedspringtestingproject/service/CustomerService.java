package org.example.updatedspringtestingproject.service;

import org.example.updatedspringtestingproject.model.Customer;
import org.example.updatedspringtestingproject.repo.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    ICustomerRepo customerRepo;

    // Save a customer
    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepo.findById(id);
    }

    // Update customer
    public Customer updateCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    // Delete customer by ID
    public void deleteCustomerById(Integer id) {
        customerRepo.deleteById(id);
    }


}

