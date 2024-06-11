package org.example.jobboardspringapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationsDTO {
    private Integer id;
    private String commentMessage;
    private Integer postID;
    //private String userName;
    private Integer userId;

    public NotificationsDTO(String commentMessage, Integer userId, Integer postID) {
        this.commentMessage = commentMessage;
        this.userId = userId;
        this.postID = postID;
    }

}
