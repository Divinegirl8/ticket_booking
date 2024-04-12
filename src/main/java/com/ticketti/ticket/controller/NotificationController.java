package com.ticketti.ticket.controller;

import com.ticketti.ticket.dtos.request.SendNotificationRequest;
import com.ticketti.ticket.dtos.response.ApiResponse;
import com.ticketti.ticket.dtos.response.SendNotificationResponse;
import com.ticketti.ticket.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notify")
    public ResponseEntity<?> notify(@RequestBody SendNotificationRequest request){
        try {
            SendNotificationResponse response = notificationService.notify(request);
            return ResponseEntity.ok().body(response);
        }catch (Exception err){
            return new ResponseEntity<>(new ApiResponse(err.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
