package com.ticketti.ticket.service.events;

import com.ticketti.ticket.dtos.request.EventCreationRequest;
import com.ticketti.ticket.dtos.response.EventCreationResponse;
import com.ticketti.ticket.exception.TicketException;

public interface EventService {
    EventCreationResponse createEvent(EventCreationRequest request, Long userId) throws TicketException;
}
