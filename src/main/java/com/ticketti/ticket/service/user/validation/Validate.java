package com.ticketti.ticket.service.user.validation;


import com.ticketti.ticket.exception.TicketException;



public class Validate {


    public static void validateUsername(String name) throws TicketException {
        if (name == null || name.trim().isEmpty()){
            throw new TicketException("name field is empty, kindly provide your name");
        }
        if (name.length() > 100){
            throw new TicketException("name must not exceed 100 number of characters");
        }
    }

    public static void validateEmail(String email) throws TicketException {

        if (email == null || email.trim().isEmpty()){
            throw new TicketException("email field is empty, kindly provide your email address");
        }

        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")){
            throw new TicketException("email address is not valid");
        }
    }

    public static void validatePassword(String password) throws TicketException {
        if (password == null|| password.trim().isEmpty()){
            throw new TicketException("password field is empty, kindly provide your password");
        }

        if (!password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()])(?!.*\\s).{8,}$")) {
            throw new TicketException("Password is too weak. It must contain letters, numbers, and special characters. The length must be greater than 7.");
        }
    }


}
