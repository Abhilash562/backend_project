package com.example.login.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.DTO.ApiResponse;
import com.example.login.Entity.Notification;
import com.example.login.Service.NotificationService;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "http://localhost:5173")
public class NotificationController {
	
	@Autowired
    private NotificationService service;

    @GetMapping("/{recipient}")
    public ApiResponse<List<Notification>> getNotifications(@PathVariable String recipient) {
        return new ApiResponse<>(true, "Notifications fetched", service.getNotifications(recipient));
    }

    @PutMapping("/{id}/read")
    public ApiResponse<Notification> markAsRead(@PathVariable Long id) {
        return new ApiResponse<>(true, "Notification marked as read", service.markAsRead(id));
    }

}
