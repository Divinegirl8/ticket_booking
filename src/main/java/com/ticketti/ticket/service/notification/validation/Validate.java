package com.ticketti.ticket.service.notification.validation;


import com.ticketti.ticket.exception.TicketException;

public class Validate {
    public static void validateEventName(String name) throws TicketException {
        if (name == null || name.trim().isEmpty()){
            throw new TicketException("name field is empty, kindly provide your name");
        }
        if (name.length() > 100){
            throw new TicketException("name must not exceed 100 number of characters");
        }
    }

    public static void validateMessage(String message) throws TicketException {
        if (message == null || message.trim().isEmpty()){
            throw new TicketException("name field is empty, kindly provide your name");
        }
        if (message.length() > 500){
            throw new TicketException("name must not exceed 100 number of characters");
        }
    }
}
