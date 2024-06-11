package org.example.notificationmicroservice.controller;

import org.example.notificationmicroservice.DTO.NotificationsDTO;
import org.example.notificationmicroservice.model.Notifications;
import org.example.notificationmicroservice.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateNotification() {
        NotificationsDTO notificationDTO = new NotificationsDTO();
        when(notificationService.createNotification(notificationDTO)).thenReturn(null);

        ResponseEntity<String> response = notificationController.createNotification(notificationDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetNotificationById_NotFound() {
        Integer id = 1;
        when(notificationService.getNotificationById(id)).thenReturn(Optional.empty());

        ResponseEntity<Notifications> response = notificationController.getNotificationById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllNotifications() {
        List<Notifications> notifications = new ArrayList<>();
        when(notificationService.getAllNotifications()).thenReturn(notifications);

        ResponseEntity<List<Notifications>> response = notificationController.getAllNotifications();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetAllNotificationsByUser() {
        Integer userId = 1;
        List<Notifications> notifications = new ArrayList<>();
        when(notificationService.getAllNotificationsByUser(userId)).thenReturn(notifications);

        List<Notifications> response = notificationController.getAllNotificationsByUser(userId);

        assertEquals(notifications, response);
    }

    @Test
    void testUpdateNotification() {
        Integer id = 1;
        Notifications notification = new Notifications();
        when(notificationService.updateNotification(notification)).thenReturn(notification);

        ResponseEntity<Notifications> response = notificationController.updateNotification(id, notification);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteNotificationById() {
        Integer id = 1;

        ResponseEntity<Void> response = notificationController.deleteNotificationById(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(notificationService, times(1)).deleteNotificationById(id);
    }
}
