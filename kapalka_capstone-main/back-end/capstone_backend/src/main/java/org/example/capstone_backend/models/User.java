package org.example.capstone_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "User_Accounts")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(nullable = false)
    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @Column(nullable = false)
    private LocalDate registrationDate;

    //Set up the registration date
    @PrePersist
    protected void onCreate() {
        registrationDate = LocalDate.now();
    }

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be in a valid format")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email must be in a valid format and end with a proper domain")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password must not be blank")
    private String password;

    @Column(nullable = false)
    @NotNull(message = "Birth date must not be blank")
    private LocalDate birthDate;

    //Relationship
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

}



