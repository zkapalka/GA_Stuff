package org.example.customermicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id; //how we identify a customer instance in our ICustomerRepository interface
    private String name;
    private String email;
    private Integer lastFourDigitsOfCard;
    private Date dob;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false, unique = true)
    private Address address;

    //address is tied solely to customer in eCommerce,
    //which means it will not have its own microservice and will be tied to customer

}
