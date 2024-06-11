package org.example.jobboardspringapplication.controller;

import org.example.jobboardspringapplication.model.Address;
import org.example.jobboardspringapplication.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    //CREATE
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        try {
            Optional<Address> existingAddressOptional = addressService.getAddressById(address.getAddressID());
            if (existingAddressOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                Address createdAddress = addressService.createAddress(address);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
            }
        } catch (Exception e) {
            logger.error("Error creating address", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //READ
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        try {
            List<Address> addresses = addressService.getAllAddresses();
            return ResponseEntity.status(HttpStatus.OK).body(addresses);
        } catch (Exception e) {
            logger.error("Error getting all addresses", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer id) {
        try {
            Optional<Address> addressOptional = addressService.getAddressById(id);
            return addressOptional.map(address -> ResponseEntity.status(HttpStatus.OK).body(address))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            logger.error("Error getting address by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Integer id, @RequestBody Address address) {
        try {
            if (addressService.getAddressById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            address.setAddressID(id);
            Address updatedAddress = addressService.saveAddress(address);
            return ResponseEntity.status(HttpStatus.OK).body(updatedAddress);
        } catch (Exception e) {
            logger.error("Error updating address", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable Integer id) {
        try {
            if (addressService.getAddressById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            addressService.deleteAddressById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error deleting address by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

