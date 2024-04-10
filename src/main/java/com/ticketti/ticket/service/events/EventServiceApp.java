package com.ticketti.ticket.service.events;

import com.ticketti.ticket.data.model.Event;
import com.ticketti.ticket.data.model.User;
import com.ticketti.ticket.data.repository.EventRepository;
import com.ticketti.ticket.data.repository.UserRepository;
import com.ticketti.ticket.dtos.request.EventCreationRequest;
import com.ticketti.ticket.dtos.response.EventCreationResponse;
import com.ticketti.ticket.exception.TicketException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ticketti.ticket.service.events.validation.Validate.*;

@Service
@AllArgsConstructor
public class EventServiceApp implements EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    @Override
    public EventCreationResponse createEvent(EventCreationRequest request,Long userId) throws TicketException {
        validateName(request.getName());
        validateEventDescription(request.getDescription());
        validateAttendee(request.getAttendeesCount());

        User user = findUserBy(userId);

        Event event = new Event();
        event.setName(request.getName());
        event.setDescription(request.getDescription());
        event.setDateTime(request.getDateTime());
        event.setAttendeesCount(request.getAttendeesCount());
        event.setCategory(request.getCategory());
        event.setUser(user);
        userRepository.save(user);
        eventRepository.save(event);


        EventCreationResponse response = new EventCreationResponse();
        response.setId(event.getId());
        return response;
    }

    private User findUserBy(Long userId) throws TicketException {
        return userRepository.findById(userId).orElseThrow(()->new TicketException("user not found"));
    }

}
