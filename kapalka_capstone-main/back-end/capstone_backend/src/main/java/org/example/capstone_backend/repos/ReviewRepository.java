package org.example.capstone_backend.repos;

import org.example.capstone_backend.models.Game;
import org.example.capstone_backend.models.Review;
import org.example.capstone_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByUser(User user);

    List<Review> findByGame(Game game);

    @Query("SELECT r FROM Review r WHERE r.rating >= 4")
    List<Review> findHighRatedReviews();
}

