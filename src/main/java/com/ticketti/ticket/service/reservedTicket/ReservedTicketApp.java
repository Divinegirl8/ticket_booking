package com.ticketti.ticket.service.reservedTicket;

import com.ticketti.ticket.data.model.ReserveTicket;
import com.ticketti.ticket.data.model.User;
import com.ticketti.ticket.data.repository.ReserveTicketRepository;
import com.ticketti.ticket.data.repository.UserRepository;
import com.ticketti.ticket.dtos.response.ReservedTicketHistoryResponse;
import com.ticketti.ticket.exception.TicketException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservedTicketApp implements ReservedTicketService{
    private final ReserveTicketRepository reserveTicketRepository;
    private final UserRepository userRepository;
    @Override
    public ReservedTicketHistoryResponse reservedTicketHistory(Long userId) throws TicketException {
        User user = findUserBy(userId);
        List<ReserveTicket> reserveTickets = reserveTicketRepository.findByUserId(user.getId());

        ReservedTicketHistoryResponse response = new ReservedTicketHistoryResponse();
        response.setReserveTickets(reserveTickets);

        return response;
    }
    private User findUserBy(Long userId) throws TicketException {
        return userRepository.findById(userId).orElseThrow(()->new TicketException("user not found"));
    }

}
