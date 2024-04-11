package com.ticketti.ticket.data.repository;

import com.ticketti.ticket.data.model.Event;
import com.ticketti.ticket.data.model.ReserveTicket;
import com.ticketti.ticket.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByName(String name);
    User findUserByEmail(String email);
    List<User> findUserByEvents(Event event);
    List<User> findUserByReserveTicket(List<ReserveTicket> ticket);


}
