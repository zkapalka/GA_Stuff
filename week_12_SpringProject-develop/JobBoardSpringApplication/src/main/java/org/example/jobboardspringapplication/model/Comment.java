package org.example.jobboardspringapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="User_Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer commentID;
    private String commentText;
    private int commentLikes;
    private LocalDateTime commentCreatedTime;

    // Relationships
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; //This fulfills the concept of the concept of commentBy

    //Set up the comment_created_date
    @PrePersist
    protected void onCreate() {
        commentCreatedTime = LocalDateTime.now();
    }
}

