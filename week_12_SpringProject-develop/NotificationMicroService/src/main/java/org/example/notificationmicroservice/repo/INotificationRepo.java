package org.example.notificationmicroservice.repo;

import org.example.notificationmicroservice.model.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INotificationRepo extends JpaRepository <Notifications, Integer> {

    List<Notifications> findByUserId(Integer userId);
}
