package org.example.capstone_backend.repos;

import org.example.capstone_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Allows to find by email
    User findByEmail(String email);
}

