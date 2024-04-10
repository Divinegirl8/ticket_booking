package com.ticketti.ticket.service.events.validation;

import com.ticketti.ticket.exception.TicketException;

public class Validate {
    public static void validateName(String name) throws TicketException {
        if (name == null || name.trim().isEmpty()){
            throw new TicketException("name field is empty, kindly provide your name");
        }
        if (name.length() > 100){
            throw new TicketException("name must not exceed 100 number of characters");
        }
    }

    public static void  validateAttendee(int count) throws TicketException {
        String attendee = String.valueOf(count);
        if (attendee.trim().isEmpty() ){
            throw new TicketException("attendee field is empty, kindly provide number of attendee");
        }

        if (count < 0){
            throw new TicketException("number of attendee cannot be less than 0");
        }
    }

    public static void validateEventDescription(String description) throws TicketException {
        if (description == null || description.trim().isEmpty()){
            throw new TicketException("description field is empty, kindly provide the event description");
        }

        if (description.length() > 500){
            throw new TicketException("The length of characters in the description field cannot exceed 500");
        }
    }

}
