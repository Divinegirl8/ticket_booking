package com.ticketti.ticket.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class Notification {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonIgnore
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private ReserveTicket ticket;
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
