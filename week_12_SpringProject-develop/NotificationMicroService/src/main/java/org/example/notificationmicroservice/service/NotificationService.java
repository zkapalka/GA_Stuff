package org.example.notificationmicroservice.service;

import jakarta.transaction.Transactional;
import org.example.notificationmicroservice.DTO.NotificationsDTO;
import org.example.notificationmicroservice.exceptions.NotificationException;
import org.example.notificationmicroservice.repo.INotificationRepo;
import org.example.notificationmicroservice.model.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final INotificationRepo notificationRepo;

    @Autowired
    public NotificationService(INotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    //Mapping DTO
    private Notifications mapToNotification(NotificationsDTO notificationsDTO) {
        Notifications notifications = new Notifications();
        notifications.setUserId(notificationsDTO.getUserId());
        notifications.setPostID(notificationsDTO.getPostID());
        notifications.setCommentMessage(notificationsDTO.getCommentMessage());

        return notifications;
    }

    // Create a new notification

    public Notifications createNotification(NotificationsDTO notificationsDTO) {
        try {
            Notifications notifications = mapToNotification(notificationsDTO);
            return notificationRepo.save(notifications);
        } catch (DataIntegrityViolationException e) {
            throw new NotificationException("Failed to create notification due to data integrity violation");
        }  catch (Exception e) {
            throw new NotificationException("Failed to create notification");
        }
    }

    // Retrieve a notification by its ID
    public Optional<Notifications> getNotificationById(Integer id) {
        try {
        return notificationRepo.findById(id);
        } catch (DataAccessException e) {
            System.err.println("An error occurred while retrieving the notification with ID " + id + ": " + e.getMessage());
            throw new NotificationException("Failed to get notification: " + e.getMessage());
        }
    }

    // Retrieve all notifications
    public List<Notifications> getAllNotifications() {
        try {
            return notificationRepo.findAll();
        } catch (DataAccessException e) {
            System.err.println("An error occurred while retrieving all notification " + e.getMessage());
            throw new NotificationException("Failed to get all notifications: " + e.getMessage());
        }
    }

    public List<Notifications> getAllNotificationsByUser(Integer userId){
        return notificationRepo.findByUserId(userId);
    }

    // Update an existing notification
    public Notifications updateNotification(Notifications notification) {
        try {
            return notificationRepo.save(notification);
        } catch (DataAccessException e) {
            System.err.println("An error occurred while updating the notification" + e.getMessage());
            throw new NotificationException("Failed to update notification: " + e.getMessage());
        }
    }

    // Delete a notification by its ID
    public void deleteNotificationById(Integer id) {
        try {
            notificationRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotificationException("notification with ID " + id + " not found");
        } catch (DataAccessException e) {
            System.err.println("An error occurred while trying to delete the notification" + e.getMessage());
            throw new NotificationException("Failed to delete notification:  " + e.getMessage());
        }
    }
}


