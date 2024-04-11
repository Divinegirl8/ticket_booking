package com.ticketti.ticket.service.notification;

import com.ticketti.ticket.data.model.Event;
import com.ticketti.ticket.data.model.Notification;
import com.ticketti.ticket.data.model.ReserveTicket;
import com.ticketti.ticket.data.model.User;
import com.ticketti.ticket.data.repository.EventRepository;
import com.ticketti.ticket.data.repository.NotificationRepository;
import com.ticketti.ticket.data.repository.ReserveTicketRepository;
import com.ticketti.ticket.data.repository.UserRepository;
import com.ticketti.ticket.dtos.request.SendNotificationRequest;
import com.ticketti.ticket.dtos.response.SendNotificationResponse;
import com.ticketti.ticket.exception.TicketException;
import com.ticketti.ticket.service.notification.validation.Validate;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ticketti.ticket.service.notification.validation.Validate.validateEventName;
import static com.ticketti.ticket.service.notification.validation.Validate.validateMessage;

@Service
@AllArgsConstructor
public class NotificationServiceApp implements NotificationService{

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ReserveTicketRepository reserveTicketRepository;
    @Override
    public SendNotificationResponse notify(SendNotificationRequest request) throws TicketException {
        validateEventName(request.getEventName());
        validateMessage(request.getMessage());

        Event event = findEvent(request.getEventName());
        List<ReserveTicket> tickets = reserveTicketRepository.findByEventName(event);
        Notification notification = new Notification();
        for (ReserveTicket ticket : tickets) {
            User user = ticket.getUser();


            System.out.println(user);
            notification.setUser(user);
            notification.setMessage(request.getMessage());
            notification.setEventName(request.getEventName());
            notificationRepository.save(notification);
        }

        SendNotificationResponse response = new SendNotificationResponse();
        response.setMessage(notification);

        return response;
    }

    private User findUserBy(Long userId) throws TicketException {
        return userRepository.findById(userId).orElseThrow(()->new TicketException("user not found"));
    }

    private Event findEvent(String eventName) throws TicketException {
        Event event = eventRepository.findFirstByName(eventName);

        if (event == null){
            throw new TicketException("Event not found");
        }

        return event;

    }


}
