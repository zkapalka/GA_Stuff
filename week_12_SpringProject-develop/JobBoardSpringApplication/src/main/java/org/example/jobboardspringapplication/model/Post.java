package org.example.jobboardspringapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="user_posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer postID;
    private String postTitle;
    private String postContent;
    private Integer postLikes;
    private String mediaUrl;
    private LocalDateTime postCreatedTime;

    // Relationships
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @PrePersist
    protected void onCreate() {
        postCreatedTime = LocalDateTime.now();
    }
}

