package org.example.jobboardspringapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer tagID;
    private String tagName;
    private String tagDescription;

    // Relationships
    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<Post> post;

    //Ensure that it is properly set up with a list if it's null
    public List<Post> getPost() {
        if (this.post == null) {
            this.post = new ArrayList<>();
        }
        return this.post;
    }

}

