package com.ticketti.ticket.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class Notification {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private String eventName;
    private String message;

    @Override
    public String toString() {
        return "Notification{" +
                "eventName='" + eventName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
