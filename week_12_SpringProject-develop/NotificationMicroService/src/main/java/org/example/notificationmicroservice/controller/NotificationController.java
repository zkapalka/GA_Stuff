package org.example.notificationmicroservice.controller;

import org.example.notificationmicroservice.DTO.NotificationsDTO;
import org.example.notificationmicroservice.exceptions.NotificationException;
import org.example.notificationmicroservice.model.Notifications;
import org.example.notificationmicroservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody NotificationsDTO notificationDTO) {
        try {
            notificationService.createNotification(notificationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Notification received and processed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process notification: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notifications> getNotificationById(@PathVariable Integer id) {
        try {
            Optional<Notifications> notificationOptional = notificationService.getNotificationById(id);
            return notificationOptional.map(notification -> ResponseEntity.ok().body(notification))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (NotificationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Notifications>> getAllNotifications() {
        try {
            List<Notifications> notifications = notificationService.getAllNotifications();
            return ResponseEntity.ok().body(notifications);
        } catch (NotificationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{userId}")
    public List<Notifications> getAllNotificationsByUser(@PathVariable Integer userId) {
        return notificationService.getAllNotificationsByUser(userId);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Notifications> updateNotification(@PathVariable Integer id, @RequestBody Notifications notification) {
        try {
            notification.setId(id);
            Notifications updatedNotification = notificationService.updateNotification(notification);
            return ResponseEntity.ok().body(updatedNotification);
        } catch (NotificationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificationById(@PathVariable Integer id) {
        try {
            notificationService.deleteNotificationById(id);
            return ResponseEntity.noContent().build();
        } catch (NotificationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
