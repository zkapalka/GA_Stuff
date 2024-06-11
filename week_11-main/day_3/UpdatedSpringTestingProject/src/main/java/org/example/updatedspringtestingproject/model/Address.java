package org.example.updatedspringtestingproject.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer addressId;

    private String street;
    private String city;
    private String state;
    private String postalCode;

    @OneToOne(mappedBy = "address")
    private Customer customer;
}

