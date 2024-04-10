package com.ticketti.ticket.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SearchEventRequest {
    private String eventName;
}
