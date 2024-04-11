package com.ticketti.ticket.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
public class ReserveTicket {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_name_id")
    private Event eventName;
    private boolean reserved;
    private int numberOfTicket;
}
