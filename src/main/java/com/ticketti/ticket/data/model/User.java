package com.ticketti.ticket.data.model;

import com.ticketti.ticket.data.model.Event;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private  Long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<ReserveTicket> reserveTicket;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", events=" + events +
                '}';
    }
}
