package com.ticketti.ticket.dtos.response;

import com.ticketti.ticket.data.model.ReserveTicket;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class ReservedTicketHistoryResponse {
    private List<ReserveTicket> reserveTickets;
}
