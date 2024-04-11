package com.ticketti.ticket.dtos.response;

import com.ticketti.ticket.data.model.Notification;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SendNotificationResponse {
    private Notification message;
}
