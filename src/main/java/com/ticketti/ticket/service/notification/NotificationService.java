package com.ticketti.ticket.service.notification;

import com.ticketti.ticket.dtos.request.SendNotificationRequest;
import com.ticketti.ticket.dtos.response.SendNotificationResponse;
import com.ticketti.ticket.exception.TicketException;

public interface NotificationService {
    SendNotificationResponse notify(SendNotificationRequest request) throws TicketException;
}
