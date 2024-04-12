package com.ticketti.ticket.service.reservedTicket;

import com.ticketti.ticket.data.model.Event;
import com.ticketti.ticket.data.model.ReserveTicket;
import com.ticketti.ticket.data.model.User;
import com.ticketti.ticket.data.repository.EventRepository;
import com.ticketti.ticket.data.repository.ReserveTicketRepository;
import com.ticketti.ticket.data.repository.UserRepository;
import com.ticketti.ticket.dtos.request.CancelReservationRequest;
import com.ticketti.ticket.dtos.request.ReserveTicketRequest;
import com.ticketti.ticket.dtos.response.CancelReservationResponse;
import com.ticketti.ticket.dtos.response.ReserveTicketResponse;
import com.ticketti.ticket.dtos.response.ReservedTicketHistoryResponse;
import com.ticketti.ticket.exception.TicketException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ticketti.ticket.data.model.ReservationStatus.CANCELLED;
import static com.ticketti.ticket.service.events.validation.Validate.validateAttendee;
import static com.ticketti.ticket.service.events.validation.Validate.validateName;
import static com.ticketti.ticket.util.Mapper.mapReserveTicket;

@Service
@AllArgsConstructor
public class ReservedTicketApp implements ReservedTicketService{
    private final ReserveTicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public ReserveTicketResponse reserveTicket(ReserveTicketRequest request, Long userId) throws TicketException {
        validateName(request.getName());
        validateName(request.getEventName());
        validateAttendee(request.getNumberOfTicket());

        User user = findUserBy(userId);
        Event event = getEvent(request);
        ReserveTicket ticket = mapReserveTicket(request, user, event);
        ticketRepository.save(ticket);

        ReserveTicketResponse response = new ReserveTicketResponse();
        response.setMessage("Ticket reserved successfully and your ticket ID is TID" + ticket.getId());
        return response;
    }

    @Override
    public CancelReservationResponse cancelReservation(CancelReservationRequest request) throws TicketException {
        String reservationTicketId = request.getTicketId();
        String extractNumber = reservationTicketId.replaceAll("\\D+","");
        Long ticketId = Long.parseLong(extractNumber);

        ReserveTicket ticket = ticketRepository.findById(ticketId).orElseThrow(()-> new TicketException("Ticket not found"));

        if (ticket.getReserved().equals(CANCELLED)){
            throw new TicketException("Reservation with ticket " + request.getTicketId() + " has already been cancelled");
        }

        Event event = ticket.getEventName();
        int ticketAvailable = event.getAttendeesCount();
        event.setAttendeesCount(ticketAvailable + ticket.getNumberOfTicket());

        ticket.setReserved(CANCELLED);

        eventRepository.save(event);
        ticketRepository.save(ticket);

        CancelReservationResponse response = new CancelReservationResponse();
        response.setMessage("Reservation has been cancelled");

        return response;
    }

    @Override
    public ReservedTicketHistoryResponse reservedTicketHistory(Long userId) throws TicketException {
        User user = findUserBy(userId);
        List<ReserveTicket> reserveTickets = ticketRepository.findByUserId(user.getId());

        ReservedTicketHistoryResponse response = new ReservedTicketHistoryResponse();
        response.setReserveTickets(reserveTickets);

        return response;
    }
    private User findUserBy(Long userId) throws TicketException {
        return userRepository.findById(userId).orElseThrow(()->new TicketException("user not found"));
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

    private Event findEvent(String eventName) throws TicketException {
        Event event = eventRepository.findFirstByName(eventName);

        if (event == null){
            throw new TicketException("Event not found");
        }

        return event;

    }


}
