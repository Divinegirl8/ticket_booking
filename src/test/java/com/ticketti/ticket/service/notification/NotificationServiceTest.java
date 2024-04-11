package com.ticketti.ticket.service.notification;

import com.ticketti.ticket.dtos.request.SendNotificationRequest;
import com.ticketti.ticket.dtos.response.SendNotificationResponse;
import com.ticketti.ticket.exception.TicketException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Test void testNotification() throws TicketException {
        SendNotificationRequest request = new SendNotificationRequest();
        request.setEventName("gaming");
        request.setMessage("This is to notify the event starts tomorrow");
       SendNotificationResponse response = notificationService.notify(request);
       log.info("{}->",response);
       assertThat(response).isNotNull();
    }

}