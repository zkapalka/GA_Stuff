package org.example.jobboardspringapplication.repo;

import org.example.jobboardspringapplication.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepo extends JpaRepository<Address, Integer> {
    //Basic CRUD already set up thanks to JPA Repo
}

