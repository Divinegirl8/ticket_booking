package com.ticketti.ticket.dtos.request;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReserveTicketRequest {
    private String name;
    private String eventName;
    private int numberOfTicket;
}
