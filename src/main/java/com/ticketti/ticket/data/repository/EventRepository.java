package com.ticketti.ticket.data.repository;

import com.ticketti.ticket.data.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
  Event findFirstByName(String name);
}
