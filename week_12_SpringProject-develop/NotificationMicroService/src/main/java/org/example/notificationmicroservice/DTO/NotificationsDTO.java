package org.example.notificationmicroservice.DTO;

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
}
