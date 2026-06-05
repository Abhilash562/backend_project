package com.example.login.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.Entity.Notification;

public interface NotificationRepository extends JpaRepository <Notification, Long> {

	List<Notification> findByRecipientOrderByCreatedAtDesc(String recipient);

}
