package com.ticketti.ticket.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private User user;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_name_id")
    @JsonIgnore
    private Event eventName;
    @Enumerated(EnumType.STRING)
    private ReservationStatus reserved;
    private int numberOfTicket;


    @JsonProperty("eventName")
    public String getEventName() {
        return eventName != null ? eventName.getName() : null;
    }

    @JsonProperty("ticketId")
    public String getId() {
        return id != null ? "TID" + id : null;
    }

    @JsonIgnore
    public Event getEvent(){
        return eventName;
    }
    @Override
    public String toString() {
        return "ReserveTicket{" +
                "id= TID" + id + '\'' +
                ", name='" + name + '\'' +
                ", eventName=" + eventName +
                ", numberOfTicket=" + numberOfTicket +
                '}';
    }
}
