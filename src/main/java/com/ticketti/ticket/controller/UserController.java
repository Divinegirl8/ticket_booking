package com.ticketti.ticket.controller;

import com.ticketti.ticket.dtos.request.UserRegistrationRequest;
import com.ticketti.ticket.dtos.response.ApiResponse;
import com.ticketti.ticket.dtos.response.UserRegistrationResponse;
import com.ticketti.ticket.exception.TicketException;
import com.ticketti.ticket.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest request){
        try {
            UserRegistrationResponse response = userService.register(request);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (Exception err) {
            return new ResponseEntity<>(new ApiResponse(err.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
