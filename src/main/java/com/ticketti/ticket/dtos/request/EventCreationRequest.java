package com.ticketti.ticket.dtos.request;


import com.ticketti.ticket.data.model.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class EventCreationRequest {
    private String name;
    private LocalDateTime dateTime;
    private int attendeesCount;
    private String description;
    private String category;
}
