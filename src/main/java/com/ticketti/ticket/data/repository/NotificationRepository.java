package com.ticketti.ticket.data.repository;

import com.ticketti.ticket.data.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

}
