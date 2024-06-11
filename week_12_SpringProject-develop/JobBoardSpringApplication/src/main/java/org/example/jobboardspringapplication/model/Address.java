package org.example.jobboardspringapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer addressID;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    // Relationships

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

