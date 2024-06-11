package org.example.jobboardspringapplication.controller;

import org.example.jobboardspringapplication.model.Address;
import org.example.jobboardspringapplication.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    private Address addressTest1;
    private Address addressTest2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Set up addressTest1
        addressTest1 = new Address();
        addressTest1.setAddressID(1);
        addressTest1.setStreet("123 Main St");

        // Set up addressTest2
        addressTest2 = new Address();
        addressTest2.setAddressID(2);
        addressTest2.setStreet("456 Elm St");
    }

    // CREATE
    @Test
    void testCreateAddress() {
        when(addressService.createAddress(any())).thenReturn(addressTest1);

        ResponseEntity<Address> responseEntity = addressController.createAddress(addressTest1);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(addressTest1, responseEntity.getBody());
        verify(addressService).createAddress(any());
    }

    @Test
    void testCreateAddress_IdAlreadyExists() {
        when(addressService.getAddressById(1)).thenReturn(Optional.of(addressTest1));

        ResponseEntity<Address> responseEntity = addressController.createAddress(addressTest1);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        verify(addressService, never()).createAddress(any());
    }


    // READ
    @Test
    void testGetAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(addressTest1);
        addresses.add(addressTest2);

        when(addressService.getAllAddresses()).thenReturn(addresses);

        ResponseEntity<List<Address>> responseEntity = addressController.getAllAddresses();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(addresses, responseEntity.getBody());
        verify(addressService).getAllAddresses();
    }

    @Test
    void testGetAddressById() {
        when(addressService.getAddressById(1)).thenReturn(Optional.of(addressTest1));

        ResponseEntity<Address> responseEntity = addressController.getAddressById(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(addressTest1, responseEntity.getBody());
        verify(addressService).getAddressById(1);
    }

    @Test
    void testGetAddressById_NotFound() {
        when(addressService.getAddressById(1)).thenReturn(Optional.empty());

        ResponseEntity<Address> responseEntity = addressController.getAddressById(1);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(addressService).getAddressById(1);
    }


    // UPDATE
    @Test
    void testUpdateAddress() {
        when(addressService.getAddressById(1)).thenReturn(Optional.of(addressTest1));
        when(addressService.saveAddress(any())).thenReturn(addressTest1);

        ResponseEntity<Address> responseEntity = addressController.updateAddress(1, addressTest1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(addressTest1, responseEntity.getBody());
        verify(addressService).getAddressById(1);
        verify(addressService).saveAddress(any());
    }

    @Test
    void testUpdateAddress_NotFound() {
        when(addressService.getAddressById(1)).thenReturn(Optional.empty());

        ResponseEntity<Address> responseEntity = addressController.updateAddress(1, new Address());

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(addressService).getAddressById(1);
        verify(addressService, never()).saveAddress(any());
    }


    // DELETE
    @Test
    void testDeleteAddressById() {
        when(addressService.getAddressById(1)).thenReturn(Optional.of(addressTest1));

        ResponseEntity<Void> responseEntity = addressController.deleteAddressById(1);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(addressService).getAddressById(1);
        verify(addressService).deleteAddressById(1);
    }

    @Test
    void testDeleteAddressById_NotFound() {
        when(addressService.getAddressById(1)).thenReturn(Optional.empty());

        ResponseEntity<Void> responseEntity = addressController.deleteAddressById(1);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(addressService).getAddressById(1);
        verify(addressService, never()).deleteAddressById(anyInt());
    }
}
