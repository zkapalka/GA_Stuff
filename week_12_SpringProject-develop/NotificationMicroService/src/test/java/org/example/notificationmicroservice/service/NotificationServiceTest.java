package org.example.notificationmicroservice.service;

import org.example.notificationmicroservice.DTO.NotificationsDTO;
import org.example.notificationmicroservice.exceptions.NotificationException;
import org.example.notificationmicroservice.model.Notifications;
import org.example.notificationmicroservice.repo.INotificationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @Mock
    private INotificationRepo notificationRepo;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateNotification() {
        NotificationsDTO notificationsDTO = new NotificationsDTO();
        Notifications notification = new Notifications();
        when(notificationRepo.save(any(Notifications.class))).thenReturn(notification);

        Notifications result = notificationService.createNotification(notificationsDTO);

        assertNotNull(result);
    }

    @Test
    void testCreateNotification_DataIntegrityViolationException() {
        NotificationsDTO notificationsDTO = new NotificationsDTO();
        when(notificationRepo.save(any(Notifications.class))).thenThrow(DataIntegrityViolationException.class);

        assertThrows(NotificationException.class, () -> notificationService.createNotification(notificationsDTO));
    }

    @Test
    void testGetNotificationById() {
        Integer id = 1;
        Notifications notification = new Notifications();
        when(notificationRepo.findById(id)).thenReturn(Optional.of(notification));

        Optional<Notifications> result = notificationService.getNotificationById(id);

        assertTrue(result.isPresent());
    }

    @Test
    void testGetNotificationById_NotFound() {
        Integer id = 1;
        when(notificationRepo.findById(id)).thenReturn(Optional.empty());

        Optional<Notifications> result = notificationService.getNotificationById(id);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllNotifications() {
        List<Notifications> notifications = Collections.emptyList();
        when(notificationRepo.findAll()).thenReturn(notifications);

        List<Notifications> result = notificationService.getAllNotifications();

        assertEquals(notifications, result);
    }

    @Test
    void testGetAllNotificationsByUser() {
        Integer userId = 1;
        List<Notifications> notifications = Collections.emptyList();
        when(notificationRepo.findByUserId(userId)).thenReturn(notifications);

        List<Notifications> result = notificationService.getAllNotificationsByUser(userId);

        assertEquals(notifications, result);
    }

    @Test
    void testUpdateNotification() {
        Notifications notification = new Notifications();
        when(notificationRepo.save(notification)).thenReturn(notification);

        Notifications result = notificationService.updateNotification(notification);

        assertNotNull(result);
    }

    @Test
    void testDeleteNotificationById() {
        Integer id = 1;

        assertDoesNotThrow(() -> notificationService.deleteNotificationById(id));
        verify(notificationRepo, times(1)).deleteById(id);
    }

    @Test
    void testDeleteNotificationById_NotFound() {
        Integer id = 1;
        doThrow(EmptyResultDataAccessException.class).when(notificationRepo).deleteById(id);

        assertThrows(NotificationException.class, () -> notificationService.deleteNotificationById(id));
    }
}
