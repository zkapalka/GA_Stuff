package org.example.notificationmicroservice.model;

import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer postID; // ID of the post
    private Integer userId; // ID of the user receiving the notification
    private String commentMessage; // Notification message

}
