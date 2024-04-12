package com.ticketti.ticket.controller;

import com.ticketti.ticket.dtos.request.CancelReservationRequest;
import com.ticketti.ticket.dtos.request.ReserveTicketRequest;
import com.ticketti.ticket.dtos.response.ApiResponse;
import com.ticketti.ticket.dtos.response.CancelReservationResponse;
import com.ticketti.ticket.dtos.response.ReserveTicketResponse;
import com.ticketti.ticket.dtos.response.ReservedTicketHistoryResponse;
import com.ticketti.ticket.service.reservedTicket.ReservedTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class ReservedTicketController {
    @Autowired
    private ReservedTicketService reservedTicketService;

    @PostMapping("reserveTicket/{userId}")
    public ResponseEntity<?> reserveTicket(@RequestBody ReserveTicketRequest request, @PathVariable("userId") Long userId){
        try {
            ReserveTicketResponse response = reservedTicketService.reserveTicket(request,userId);
            return ResponseEntity.ok().body(response);
        }catch (Exception err){
            return new ResponseEntity<>(new ApiResponse(err.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("cancelReservation/{userId}")
    public ResponseEntity<?> cancelReservation(@RequestBody CancelReservationRequest request,@PathVariable("userId") Long userId){
        try {
            CancelReservationResponse response = reservedTicketService.cancelReservation(request,userId);
            return ResponseEntity.ok().body(response);
        }catch (Exception err){
            return new ResponseEntity<>(new ApiResponse(err.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("userReservationHistory/{userId}")
    public ResponseEntity<?> userReservationHistory(@PathVariable("userId") Long userId){
        try {
            ReservedTicketHistoryResponse response = reservedTicketService.reservedTicketHistory(userId);
            return ResponseEntity.ok().body(response);
        }catch (Exception err){
            return new ResponseEntity<>(new ApiResponse(err.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
