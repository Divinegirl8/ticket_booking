package com.ticketti.ticket.service.reservedTicket;

import com.ticketti.ticket.dtos.response.ReservedTicketHistoryResponse;
import com.ticketti.ticket.exception.TicketException;

public interface ReservedTicketService {
    ReservedTicketHistoryResponse reservedTicketHistory(Long userId) throws TicketException;
}
