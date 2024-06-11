package org.example.jobboardspringapplication.services;

import org.example.jobboardspringapplication.exceptions.AddressException;
import org.example.jobboardspringapplication.model.Address;
import org.example.jobboardspringapplication.repo.IAddressRepo;
import org.example.jobboardspringapplication.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    @Mock
    private IAddressRepo addressRepo;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateAddress() {
        Address address = new Address();
        when(addressRepo.save(any(Address.class))).thenReturn(address);

        Address result = addressService.createAddress(address);

        assertNotNull(result);
    }

    @Test
    void testCreateAddress_DataIntegrityViolationException() {
        Address address = new Address();
        when(addressRepo.save(any(Address.class))).thenThrow(DataIntegrityViolationException.class);

        assertThrows(AddressException.class, () -> addressService.createAddress(address));
    }

    @Test
    void testGetAllAddresses() {
        List<Address> addresses = Collections.emptyList();
        when(addressRepo.findAll()).thenReturn(addresses);

        List<Address> result = addressService.getAllAddresses();

        assertEquals(addresses, result);
    }

    @Test
    void testGetAddressById() {
        Integer id = 1;
        Address address = new Address();
        when(addressRepo.findById(id)).thenReturn(Optional.of(address));

        Optional<Address> result = addressService.getAddressById(id);

        assertTrue(result.isPresent());
    }

    @Test
    void testGetAddressById_NotFound() {
        Integer id = 1;
        when(addressRepo.findById(id)).thenReturn(Optional.empty());

        Optional<Address> result = addressService.getAddressById(id);

        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveAddress() {
        Address address = new Address();
        when(addressRepo.save(address)).thenReturn(address);

        Address result = addressService.saveAddress(address);

        assertNotNull(result);
    }

    @Test
    void testDeleteAddressById() {
        Integer id = 1;

        assertDoesNotThrow(() -> addressService.deleteAddressById(id));
        verify(addressRepo, times(1)).deleteById(id);
    }

    @Test
    void testDeleteAddressById_NotFound() {
        Integer id = 1;
        doThrow(EmptyResultDataAccessException.class).when(addressRepo).deleteById(id);

        assertThrows(AddressException.class, () -> addressService.deleteAddressById(id));
    }
}
