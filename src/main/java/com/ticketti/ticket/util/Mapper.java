package com.ticketti.ticket.util;

import com.ticketti.ticket.data.model.Category;
import com.ticketti.ticket.data.model.Event;
import com.ticketti.ticket.data.model.ReserveTicket;
import com.ticketti.ticket.data.model.User;
import com.ticketti.ticket.dtos.request.ReserveTicketRequest;

import java.time.LocalDateTime;

import static com.ticketti.ticket.data.model.ReservationStatus.RESERVED;

public class Mapper {
    public static User mapUser(String name,String emailAddress,String password){
        User user = new User();

        user.setName(name);
        user.setEmail(emailAddress);
        user.setPassword(password);

        return user;
    }
    public static Event mapCreateEvent(String name, String description, LocalDateTime dateTime, int attendees, Category category,User user){
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        event.setDateTime(dateTime);
        event.setAttendeesCount(attendees);
        event.setCategory(category);
        event.setUser(user);
        event.setNumberOfAttendee(String.valueOf(attendees));
        return event;
    }
    public static ReserveTicket mapReserveTicket(ReserveTicketRequest request, User user, Event event) {
        ReserveTicket ticket = new ReserveTicket();
        ticket.setUser(user);
        ticket.setName(request.getName());
        ticket.setEventName(event);
        ticket.setNumberOfTicket(request.getNumberOfTicket());
        ticket.setReserved(RESERVED);
        return ticket;
    }
}
