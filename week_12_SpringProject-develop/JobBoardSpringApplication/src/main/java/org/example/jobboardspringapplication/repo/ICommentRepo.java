package org.example.jobboardspringapplication.repo;

import org.example.jobboardspringapplication.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepo extends JpaRepository<Comment, Integer> {
}
