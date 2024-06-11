package org.example.jobboardspringapplication.repo;

import org.example.jobboardspringapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Integer> {

    //Check for email
    boolean existsByEmail(String email);
}
