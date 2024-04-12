package com.ticketti.ticket.service.reservedTicket;

import com.ticketti.ticket.dtos.request.CancelReservationRequest;
import com.ticketti.ticket.dtos.request.ReserveTicketRequest;
import com.ticketti.ticket.dtos.response.CancelReservationResponse;
import com.ticketti.ticket.dtos.response.ReserveTicketResponse;
import com.ticketti.ticket.dtos.response.ReservedTicketHistoryResponse;
import com.ticketti.ticket.exception.TicketException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ReservedTicketServiceTest {
    @Autowired
    private ReservedTicketService reservedTicketService;


    @Test
    void test_That_User_Can_Reserve_Ticket_If_Available() throws TicketException {
        ReserveTicketRequest request = new ReserveTicketRequest();
        request.setName("Amanda");
        request.setEventName(("she can code africa"));
        request.setNumberOfTicket(5);
        ReserveTicketResponse response = reservedTicketService.reserveTicket(request,1L);
        assertThat(response).isNotNull();
        log.info("{}->",response);
    }

    @Test
    void test_That_User_Cannot_Reserve_Ticket_If_Not_Available() throws TicketException {
        ReserveTicketRequest request = new ReserveTicketRequest();
        request.setName("Anabel");
        request.setEventName(("java techie"));
        request.setNumberOfTicket(2);
        reservedTicketService.reserveTicket(request,3L);
        assertThrows(TicketException.class,()->reservedTicketService.reserveTicket(request,1L));
    }

    @Test
    void test_That_User_Can_Reserve_Ticket_If_Available2() throws TicketException {
        ReserveTicketRequest request = new ReserveTicketRequest();
        request.setName("Mimi");
        request.setEventName(("Gaming"));
        request.setNumberOfTicket(4);
        ReserveTicketResponse response = reservedTicketService.reserveTicket(request,1L);
        assertThat(response).isNotNull();
        log.info("{}->",response);
    }

    @Test
    void test_That_User_Can_Reserve_Ticket_If_Available3() throws TicketException {
        ReserveTicketRequest request = new ReserveTicketRequest();
        request.setName("Vincent");
        request.setEventName(("Gaming"));
        request.setNumberOfTicket(1);
        ReserveTicketResponse response = reservedTicketService.reserveTicket(request,2L);
        assertThat(response).isNotNull();
        log.info("{}->",response);
    }


    @Test
    void test_That_User_Can_Reserve_Ticket_If_Available4() throws TicketException {
        ReserveTicketRequest request = new ReserveTicketRequest();
        request.setName("Thomas");
        request.setEventName(("Pencil unbroken"));
        request.setNumberOfTicket(2);
        ReserveTicketResponse response = reservedTicketService.reserveTicket(request,3L);
        assertThat(response).isNotNull();
        log.info("{}->",response);
    }

    @Test
    void test_That_User_Can_Reserve_Ticket_If_Available5() throws TicketException {
        ReserveTicketRequest request = new ReserveTicketRequest();
        request.setName("Evans");
        request.setEventName(("Pencil unbroken"));
        request.setNumberOfTicket(3);
        ReserveTicketResponse response = reservedTicketService.reserveTicket(request,1L);
        assertThat(response).isNotNull();
        log.info("{}->",response);
    }

    @Test
    void test_That_User_Can_Reserve_Ticket_If_Available6() throws TicketException {
        ReserveTicketRequest request = new ReserveTicketRequest();
        request.setName("Lilian");
        request.setEventName(("Pencil unbroken"));
        request.setNumberOfTicket(2);
        ReserveTicketResponse response = reservedTicketService.reserveTicket(request,2L);
        assertThat(response).isNotNull();
        log.info("{}->",response);
    }

    @Test
    void test_That_A_User_Can_Cancel_Reserved_Ticket() throws TicketException {
        CancelReservationRequest request = new CancelReservationRequest();
        request.setTicketId("TID6");
        CancelReservationResponse response = reservedTicketService.cancelReservation(request,1L);
        assertThat(response).isNotNull();
        log.info("{}->",response);
    }

    @Test
    void test_That_A_User_Can_Cancel_Reserved_Ticket2() throws TicketException {
        CancelReservationRequest request = new CancelReservationRequest();
        request.setTicketId("TID7");
        CancelReservationResponse response = reservedTicketService.cancelReservation(request,2L);
        assertThat(response).isNotNull();
        log.info("{}->",response);
    }

    @Test
    void test_That_A_User_Cannot_Cancel_Reserved_Ticket_That_Has_Been_Cancelled_Before() throws TicketException {
        CancelReservationRequest request = new CancelReservationRequest();
        request.setTicketId("TID1");

        assertThrows(TicketException.class,()-> reservedTicketService.cancelReservation(request,1L));
    }

    @Test
    void test_That_A_User_Cannot_Cancel_Reserved_Ticket_That_Does_Not_Exist() {
        CancelReservationRequest request = new CancelReservationRequest();
        request.setTicketId("TID0");
        assertThrows(TicketException.class,()->reservedTicketService.cancelReservation(request,1L));
    }

    @Test
    void test_User_Booked_History() throws TicketException {
        ReservedTicketHistoryResponse response = reservedTicketService.reservedTicketHistory(1L);
        assertThat(response).isNotNull();
        log.info("{}->",response);
    }

}