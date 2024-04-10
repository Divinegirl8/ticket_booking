package com.ticketti.ticket.service.events;

import com.ticketti.ticket.dtos.request.EventCreationRequest;
import com.ticketti.ticket.dtos.request.ReserveTicketRequest;
import com.ticketti.ticket.dtos.request.SearchEventRequest;
import com.ticketti.ticket.dtos.response.EventCreationResponse;
import com.ticketti.ticket.dtos.response.ReserveTicketResponse;
import com.ticketti.ticket.dtos.response.SearchEventResponse;
import com.ticketti.ticket.dtos.response.UserEventsResponse;
import com.ticketti.ticket.exception.TicketException;


public interface EventService {
    EventCreationResponse createEvent(EventCreationRequest request, Long userId) throws TicketException;

    SearchEventResponse searchEvent(SearchEventRequest request) throws TicketException;
    ReserveTicketResponse reserveTicket(ReserveTicketRequest request,Long userId) throws TicketException;

    UserEventsResponse findUserEvents(Long userId) throws TicketException;




}
