package com.ticketti.ticket.service.user;

import com.ticketti.ticket.dtos.request.UserRegistrationRequest;
import com.ticketti.ticket.dtos.response.UserRegistrationResponse;
import com.ticketti.ticket.exception.TicketException;

public interface UserService {
    UserRegistrationResponse register(UserRegistrationRequest request) throws TicketException;
}
