package com.ticketti.ticket.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SendNotificationRequest {
    private String eventName;
    private String message;
}
