package com.ticketti.ticket.service.events;

import com.ticketti.ticket.dtos.request.CancelReservationRequest;
import com.ticketti.ticket.dtos.request.EventCreationRequest;
import com.ticketti.ticket.dtos.request.ReserveTicketRequest;
import com.ticketti.ticket.dtos.request.SearchEventRequest;
import com.ticketti.ticket.dtos.response.*;
import com.ticketti.ticket.exception.TicketException;


public interface EventService {
    EventCreationResponse createEvent(EventCreationRequest request, Long userId) throws TicketException;

    SearchEventResponse searchEvent(SearchEventRequest request) throws TicketException;

    UserEventsResponse findUserEvents(Long userId) throws TicketException;





}
