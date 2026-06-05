package com.example.login.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.Entity.Notification;
import com.example.login.Repository.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
    private NotificationRepository notificationRepository;

    public Notification createNotification(String title, String message, String recipient) {
        Notification notification = Notification.builder()
            .title(title)
            .message(message)
            .recipient(recipient)
            .build();
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotifications(String recipient) {
        return notificationRepository.findByRecipientOrderByCreatedAtDesc(recipient);
    }

    public Notification markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setIsRead(true);
        return notificationRepository.save(notification);
    }

}
