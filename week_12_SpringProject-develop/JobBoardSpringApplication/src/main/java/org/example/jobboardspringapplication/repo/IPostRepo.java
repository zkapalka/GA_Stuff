package org.example.jobboardspringapplication.repo;

import org.example.jobboardspringapplication.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post, Integer> {
}
