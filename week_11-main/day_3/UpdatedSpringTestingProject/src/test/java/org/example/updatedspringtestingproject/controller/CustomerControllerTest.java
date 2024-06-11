package org.example.updatedspringtestingproject.controller;

import org.example.updatedspringtestingproject.model.Customer;
import org.example.updatedspringtestingproject.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testCreateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("John Doe");

        when(customerService.saveCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(customerService).saveCustomer(any(Customer.class));
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("John Doe");

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Jane Smith");

        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));

        verify(customerService).getAllCustomers();
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");

        when(customerService.getCustomerById(1)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(customerService).getCustomerById(1);
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        // Prepare a customer with the original name
        Customer originalCustomer = new Customer();
        originalCustomer.setId(1);
        originalCustomer.setName("John Doe");

        // Prepare a customer with the updated name
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(1);
        updatedCustomer.setName("Updated Name");

        // Mock the behavior of the service layer methods
        when(customerService.getCustomerById(1)).thenReturn(Optional.of(originalCustomer));
        when(customerService.updateCustomer(any(Customer.class))).thenReturn(updatedCustomer);

        // Perform the PUT request to update the customer's name
        mockMvc.perform(put("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Name"));

        // Verify that the service layer methods were called with the correct parameters
        verify(customerService).getCustomerById(1);
        verify(customerService).updateCustomer(any(Customer.class));
    }


    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isNoContent());

        verify(customerService).deleteCustomerById(1);
    }


}
