package com.ticketti.ticket.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
public class ReserveTicket {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    @OneToOne(fetch = FetchType.EAGER)
    private Event eventName;
    private boolean reserved;
    private int numberOfTicket;
}
