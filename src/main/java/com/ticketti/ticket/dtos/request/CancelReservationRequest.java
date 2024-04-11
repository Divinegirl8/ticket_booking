package com.ticketti.ticket.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CancelReservationRequest {
    private String ticketId;
}
