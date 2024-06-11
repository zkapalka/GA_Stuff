package org.example.jobboardspringapplication.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User_Accounts")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer userID;
    private String userName;
    private String email;
    private String password;
    private LocalDate registrationDate;
    private String profilePictureUrl;
    private String bio;

    // Relationships
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    //Set up the registration date
    @PrePersist
    protected void onCreate() {
        registrationDate = LocalDate.now();
    }
}
