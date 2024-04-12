package com.ticketti.ticket.data.repository;

import com.ticketti.ticket.data.model.Event;
import com.ticketti.ticket.data.model.ReserveTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveTicketRepository extends JpaRepository<ReserveTicket,Long> {

    List<ReserveTicket> findByEventName(Event event);
    List<ReserveTicket> findByUserId(Long userId);
ReserveTicket findByIdAndUserId(Long id, Long user_id);


}
