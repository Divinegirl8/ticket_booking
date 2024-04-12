package com.ticketti.ticket.service.events;

import com.ticketti.ticket.data.model.Event;
import com.ticketti.ticket.data.model.User;
import com.ticketti.ticket.data.repository.EventRepository;
import com.ticketti.ticket.data.repository.UserRepository;
import com.ticketti.ticket.dtos.request.EventCreationRequest;
import com.ticketti.ticket.dtos.request.SearchEventRequest;
import com.ticketti.ticket.dtos.response.*;
import com.ticketti.ticket.exception.TicketException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import static com.ticketti.ticket.service.events.validation.Validate.*;
import static com.ticketti.ticket.util.Mapper.mapCreateEvent;


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
        checkIfEventNameExist(request);

        User user = findUserBy(userId);

        Event event = mapCreateEvent(request.getName(),request.getDescription(),request.getDateTime(),request.getAttendeesCount(),request.getCategory(),user);
        userRepository.save(user);
        eventRepository.save(event);

        EventCreationResponse response = new EventCreationResponse();
        response.setId(event.getId());
        return response;
    }

    private void checkIfEventNameExist(EventCreationRequest request) throws TicketException {
        Event eventFound = eventRepository.findFirstByName(request.getName());

        if (eventFound != null){
            throw new TicketException("An event with this name already exist");
        }
    }

    @Override
    public SearchEventResponse searchEvent(SearchEventRequest request) throws TicketException {
        validateName(request.getEventName());
        Event event = findEvent(request.getEventName());

        SearchEventResponse response = new SearchEventResponse();
        response.setEvent(event);
        return response;
    }



    @Override
    public UserEventsResponse findUserEvents(Long userId) throws TicketException {
        User user = findUserBy(userId);

        UserEventsResponse response = new UserEventsResponse();
        response.setEvents(user.getEvents());
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
