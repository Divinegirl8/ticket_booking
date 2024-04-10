package com.ticketti.ticket.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticketti.ticket.data.model.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class EventCreationRequest {
    private String name;
    private LocalDateTime dateTime;
    private int attendeesCount;
    private String description;
    private Category category;
}
