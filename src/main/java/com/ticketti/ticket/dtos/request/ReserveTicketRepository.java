package com.ticketti.ticket.dtos.request;

import com.ticketti.ticket.data.model.ReserveTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveTicketRepository extends JpaRepository<ReserveTicket,Long> {
}
