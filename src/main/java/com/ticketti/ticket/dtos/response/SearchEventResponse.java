package com.ticketti.ticket.dtos.response;

import com.ticketti.ticket.data.model.Event;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SearchEventResponse {
    private Event event;
}
