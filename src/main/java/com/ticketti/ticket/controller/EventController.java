package com.ticketti.ticket.controller;

import com.ticketti.ticket.dtos.request.EventCreationRequest;
import com.ticketti.ticket.dtos.request.SearchEventRequest;
import com.ticketti.ticket.dtos.response.ApiResponse;
import com.ticketti.ticket.dtos.response.EventCreationResponse;
import com.ticketti.ticket.dtos.response.SearchEventResponse;
import com.ticketti.ticket.dtos.response.UserEventsResponse;
import com.ticketti.ticket.exception.TicketException;
import com.ticketti.ticket.service.events.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("createEvent/{userId}")
    public ResponseEntity<?> createEvent(@RequestBody EventCreationRequest request, @PathVariable("userId") Long userId){
        try {
            EventCreationResponse response = eventService.createEvent(request,userId);
            return ResponseEntity.ok().body(response);
        } catch (Exception err) {
            return new ResponseEntity<>(new ApiResponse(err.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("searchEvent")
    public ResponseEntity<?> searchEvent(@RequestBody SearchEventRequest request){
        try {
            SearchEventResponse response = eventService.searchEvent(request);
            return ResponseEntity.ok().body(response);
        }catch (Exception err){
            return new ResponseEntity<>(new ApiResponse(err.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("userCreatedEvents/{userId}")
    public ResponseEntity<?> usersEvent(@PathVariable("userId") Long userId){
        try {
            UserEventsResponse response = eventService.findUserEvents(userId);
            return ResponseEntity.ok().body(response);
        } catch (Exception err) {
            return new ResponseEntity<>(new ApiResponse(err.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
