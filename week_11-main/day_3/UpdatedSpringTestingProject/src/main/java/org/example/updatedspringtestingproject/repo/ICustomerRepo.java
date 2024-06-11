package org.example.updatedspringtestingproject.repo;

import org.example.updatedspringtestingproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Integer> {
    //basic CRUD already included
}


