package com.ticketti.ticket.service.notification;

import com.ticketti.ticket.data.model.Event;
import com.ticketti.ticket.data.model.Notification;
import com.ticketti.ticket.data.model.ReserveTicket;
import com.ticketti.ticket.data.repository.EventRepository;
import com.ticketti.ticket.data.repository.NotificationRepository;
import com.ticketti.ticket.data.repository.ReserveTicketRepository;
import com.ticketti.ticket.dtos.request.SendNotificationRequest;
import com.ticketti.ticket.dtos.response.SendNotificationResponse;
import com.ticketti.ticket.exception.TicketException;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ticketti.ticket.service.notification.validation.Validate.validateEventName;
import static com.ticketti.ticket.service.notification.validation.Validate.validateMessage;

@Service
@AllArgsConstructor
public class NotificationServiceApp implements NotificationService{

    private final NotificationRepository notificationRepository;
    private final EventRepository eventRepository;
    private final ReserveTicketRepository reserveTicketRepository;
    @Override
    public SendNotificationResponse notify(SendNotificationRequest request) throws TicketException {
        validateEventName(request.getEventName());
        validateMessage(request.getMessage());


        Event event = findEvent(request.getEventName());
        List<ReserveTicket> tickets = reserveTicketRepository.findByEventName(event);

        List<Notification> notifications = new ArrayList<>();
        Notification notification = getNotification(request, tickets, notifications);

        SendNotificationResponse response = new SendNotificationResponse();
        response.setMessage(notification);

        return response;
    }

    private Notification getNotification(SendNotificationRequest request, List<ReserveTicket> tickets, List<Notification> notifications) {
        Notification notification = new Notification();
        for (ReserveTicket ticket : tickets) {
            notification.setTicket(ticket);
            notification.setMessage(request.getMessage());
            notification.setEventName(request.getEventName());
            notifications.add(notification);

            notificationRepository.saveAll(notifications);

        }
        return notification;
    }



    private Event findEvent(String eventName) throws TicketException {
        Event event = eventRepository.findFirstByName(eventName);

        if (event == null){
            throw new TicketException("Event not found");
        }

        return event;

    }


}
