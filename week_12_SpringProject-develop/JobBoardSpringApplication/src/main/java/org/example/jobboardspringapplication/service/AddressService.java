package org.example.jobboardspringapplication.service;

import org.example.jobboardspringapplication.exceptions.AddressException;
import org.example.jobboardspringapplication.model.Address;
import org.example.jobboardspringapplication.repo.IAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessResourceFailureException;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {


    private final IAddressRepo addressRepo;
    @Autowired
    public AddressService(IAddressRepo addressRepo){
        this.addressRepo = addressRepo;
    }

    public Address createAddress(Address address) {
        try {
            return addressRepo.save(address);
        } catch (DataIntegrityViolationException e) {
            System.err.println("Error creating address: " + e.getMessage());
            throw new AddressException("Failed to create address: " + e.getMessage());
        }
    }

    public List<Address> getAllAddresses() {
        try {
            return addressRepo.findAll();
        } catch (DataAccessException e) {
            System.err.println("An error occurred while retrieving addresses: " + e.getMessage());
            throw new DataAccessResourceFailureException("Failed to retrieve addresses", e);
        }
    }

    public Optional<Address> getAddressById(Integer id) {
        try {
            return addressRepo.findById(id);
        } catch (DataAccessException e) {
            System.err.println("An error occurred while retrieving the address with ID " + id + ": " + e.getMessage());
            throw new AddressException("Failed to get address: " + e.getMessage());
        }
    }

    public Address saveAddress(Address address) {
        try {
              return addressRepo.save(address);
        } catch (DataAccessException e) {
            System.err.println("An error occurred while updating the address" + e.getMessage());
            throw new AddressException("Failed to update address: " + e.getMessage());
        }
    }

    public void deleteAddressById(Integer id) {
        try {
            addressRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new AddressException("Address with ID " + id + " not found");
        } catch (DataAccessException e) {
            System.err.println("An error occurred while trying to delete the address" + e.getMessage());
            throw new AddressException("Failed to delete address:  " + e.getMessage());
        }
    }
}
