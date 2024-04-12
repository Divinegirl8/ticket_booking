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

    @Test void testNotification2() throws TicketException {
        SendNotificationRequest request = new SendNotificationRequest();
        request.setEventName("she can code africa");
        request.setMessage("she can code africa conference starts tomorrow");
        SendNotificationResponse response = notificationService.notify(request);
        log.info("{}->",response);
        assertThat(response).isNotNull();
    }

    @Test void testNotification3() throws TicketException {
        SendNotificationRequest request = new SendNotificationRequest();
        request.setEventName("pencil unbroken");
        request.setMessage("The event starts tomorrow");
        SendNotificationResponse response = notificationService.notify(request);
        log.info("{}->",response);
        assertThat(response).isNotNull();
    }

    @Test void testNotification4() throws TicketException {
        SendNotificationRequest request = new SendNotificationRequest();
        request.setEventName("femi");
        request.setMessage("The event starts tomorrow,getting ready");
        SendNotificationResponse response = notificationService.notify(request);
        log.info("{}->",response);
        assertThat(response).isNotNull();
    }

}