package com.ticketti.ticket.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String numberOfAttendee;
    private LocalDateTime dateTime;
    private int attendeesCount;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", attendees=" + numberOfAttendee +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
