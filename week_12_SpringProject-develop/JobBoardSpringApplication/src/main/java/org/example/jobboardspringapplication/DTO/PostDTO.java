package org.example.jobboardspringapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String postTitle;
    private String postContent;
    private Integer postLikes;
    private String mediaUrl;
    private Integer userID;

}
