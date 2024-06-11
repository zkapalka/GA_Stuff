package org.example.jobboardspringapplication.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private String commentText;
    private int commentLikes;
    private Integer postID;
    private Integer userID;
}
