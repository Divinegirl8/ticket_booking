package com.ticketti.ticket.service.events;

import com.ticketti.ticket.data.model.Event;
import com.ticketti.ticket.data.model.ReserveTicket;
import com.ticketti.ticket.data.model.User;
import com.ticketti.ticket.data.repository.EventRepository;
import com.ticketti.ticket.data.repository.UserRepository;
import com.ticketti.ticket.dtos.request.CancelReservationRequest;
import com.ticketti.ticket.dtos.request.EventCreationRequest;
import com.ticketti.ticket.data.repository.ReserveTicketRepository;
import com.ticketti.ticket.dtos.request.ReserveTicketRequest;
import com.ticketti.ticket.dtos.request.SearchEventRequest;
import com.ticketti.ticket.dtos.response.*;
import com.ticketti.ticket.exception.TicketException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ticketti.ticket.service.events.validation.Validate.*;

@Service
@AllArgsConstructor
public class EventServiceApp implements EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ReserveTicketRepository ticketRepository;
    @Override
    public EventCreationResponse createEvent(EventCreationRequest request,Long userId) throws TicketException {
        validateName(request.getName());
        validateEventDescription(request.getDescription());
        validateAttendee(request.getAttendeesCount());
        checkIfEventNameExist(request);

        User user = findUserBy(userId);

        Event event = new Event();
        event.setName(request.getName());
        event.setDescription(request.getDescription());
        event.setDateTime(request.getDateTime());
        event.setAttendeesCount(request.getAttendeesCount());
        event.setCategory(request.getCategory());
        event.setUser(user);
        event.setNumberOfAttendee(String.valueOf(request.getAttendeesCount()));
        userRepository.save(user);
        eventRepository.save(event);


        EventCreationResponse response = new EventCreationResponse();
        response.setId(event.getId());
        return response;
    }

    private void checkIfEventNameExist(EventCreationRequest request) throws TicketException {
        Event eventFound = eventRepository.findFirstByName(request.getName());

        if (eventFound != null){
            throw new TicketException("An event with this name already exist");
        }
    }

    @Override
    public SearchEventResponse searchEvent(SearchEventRequest request) throws TicketException {
        validateName(request.getEventName());
        Event event = findEvent(request.getEventName());

        SearchEventResponse response = new SearchEventResponse();
        response.setEvent(event);
        return response;
    }

    @Override
    public ReserveTicketResponse reserveTicket(ReserveTicketRequest request, Long userId) throws TicketException {
        validateName(request.getName());
        validateName(request.getEventName());
        validateAttendee(request.getNumberOfTicket());

        User user = findUserBy(userId);
        Event event = getEvent(request);
        ReserveTicket ticket = getReserveTicket(request, user, event);

        ReserveTicketResponse response = new ReserveTicketResponse();
        response.setMessage("Ticket reserved successfully and your ticket ID is TID" + ticket.getId());
        return response;
    }

    private ReserveTicket getReserveTicket(ReserveTicketRequest request, User user, Event event) {
        ReserveTicket ticket = new ReserveTicket();
        ticket.setUser(user);
        ticket.setName(request.getName());
        ticket.setEventName(event);
        ticket.setNumberOfTicket(request.getNumberOfTicket());
        ticket.setReserved(true);
        ticketRepository.save(ticket);
        return ticket;
    }

    private Event getEvent(ReserveTicketRequest request) throws TicketException {
        Event event = findEvent(request.getEventName());

        int ticketsAvailable = event.getAttendeesCount();

        if (request.getNumberOfTicket() > 5){
            throw new TicketException("You cannot reserve more than 5 tickets");
        }


        if (request.getNumberOfTicket() > ticketsAvailable){
            throw new TicketException("Available ticket for the event is " + ticketsAvailable);
        }


        if (ticketsAvailable == 0) {
            throw new TicketException("No more tickets available for " + event.getName());
        }

        event.setAttendeesCount(ticketsAvailable - request.getNumberOfTicket());

        eventRepository.save(event);
        return event;
    }

    @Override
    public UserEventsResponse findUserEvents(Long userId) throws TicketException {
        User user = findUserBy(userId);

        UserEventsResponse response = new UserEventsResponse();
        response.setEvents(user.getEvents());
        return response;
    }

    @Override
    public CancelReservationResponse cancelReservation(CancelReservationRequest request) throws TicketException {
        String reservationTicketId = request.getTicketId();
        String extractNumber = reservationTicketId.replaceAll("\\D+","");
        Long ticketId = Long.parseLong(extractNumber);

        ReserveTicket ticket = ticketRepository.findById(ticketId).orElseThrow(()-> new TicketException("Ticket not found"));

        Event event = ticket.getEventName();
        int ticketAvailable = event.getAttendeesCount();
        event.setAttendeesCount(ticketAvailable + ticket.getNumberOfTicket());

        ticket.setReserved(false);

        eventRepository.save(event);
        ticketRepository.save(ticket);

        CancelReservationResponse response = new CancelReservationResponse();
        response.setMessage("Reservation has been cancelled");

        return response;
    }


    private User findUserBy(Long userId) throws TicketException {
        return userRepository.findById(userId).orElseThrow(()->new TicketException("user not found"));
    }

    private Event findEvent(String eventName) throws TicketException {
        Event event = eventRepository.findFirstByName(eventName);

        if (event == null){
            throw new TicketException("Event not found");
        }

        return event;

    }

}
