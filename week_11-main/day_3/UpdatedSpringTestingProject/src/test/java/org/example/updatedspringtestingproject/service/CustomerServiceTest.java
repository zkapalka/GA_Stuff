package org.example.updatedspringtestingproject.service;

import org.example.updatedspringtestingproject.model.Customer;
import org.example.updatedspringtestingproject.repo.ICustomerRepo;
import org.example.updatedspringtestingproject.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private ICustomerRepo customerRepo;


    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");

        when(customerRepo.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.saveCustomer(customer);

        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getName());

        verify(customerRepo).save(any(Customer.class));
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("John Doe");

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Jane Smith");

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        when(customerRepo.findAll()).thenReturn(customers);

        List<Customer> retrievedCustomers = customerService.getAllCustomers();

        assertEquals(2, retrievedCustomers.size());
        assertEquals("John Doe", retrievedCustomers.get(0).getName());
        assertEquals("Jane Smith", retrievedCustomers.get(1).getName());

        verify(customerRepo).findAll();
    }
    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");

        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));

        Optional<Customer> retrievedCustomer = customerService.getCustomerById(1);

        assertTrue(retrievedCustomer.isPresent());
        assertEquals("John Doe", retrievedCustomer.get().getName());

        verify(customerRepo).findById(1);
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");

        when(customerRepo.save(any(Customer.class))).thenReturn(customer);

        Customer updatedCustomer = customerService.updateCustomer(customer);

        assertNotNull(updatedCustomer);
        assertEquals("John Doe", updatedCustomer.getName());

        verify(customerRepo).save(any(Customer.class));
    }

    @Test
    public void testDeleteCustomerById() {
        // Perform the test without asserting anything since the method returns void
        customerService.deleteCustomerById(1);

        verify(customerRepo).deleteById(1);
    }


}
