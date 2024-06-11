package org.example.jobboardspringapplication.repo;

import org.example.jobboardspringapplication.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITagRepo extends JpaRepository<Tag, Integer> {

}
