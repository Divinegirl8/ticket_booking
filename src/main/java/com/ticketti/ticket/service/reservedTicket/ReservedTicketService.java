package com.ticketti.ticket.service.reservedTicket;

import com.ticketti.ticket.dtos.request.CancelReservationRequest;
import com.ticketti.ticket.dtos.request.ReserveTicketRequest;
import com.ticketti.ticket.dtos.response.CancelReservationResponse;
import com.ticketti.ticket.dtos.response.ReserveTicketResponse;
import com.ticketti.ticket.dtos.response.ReservedTicketHistoryResponse;
import com.ticketti.ticket.exception.TicketException;

public interface ReservedTicketService {
    ReserveTicketResponse reserveTicket(ReserveTicketRequest request, Long userId) throws TicketException;
    CancelReservationResponse cancelReservation(CancelReservationRequest request) throws TicketException;

    ReservedTicketHistoryResponse reservedTicketHistory(Long userId) throws TicketException;
}
