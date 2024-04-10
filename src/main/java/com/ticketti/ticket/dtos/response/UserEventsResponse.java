package com.ticketti.ticket.dtos.response;

import com.ticketti.ticket.data.model.Event;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class UserEventsResponse {
    private List<Event> events;
}
