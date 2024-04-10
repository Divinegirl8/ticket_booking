package com.ticketti.ticket.dtos.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserRegistrationRequest {
    private String name;
    private String email;
    private String password;
}
