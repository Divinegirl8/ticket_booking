package com.ticketti.ticket.service.reservedTicket;

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
    void test_User_Booked_History() throws TicketException {
        ReservedTicketHistoryResponse response = reservedTicketService.reservedTicketHistory(1L);
        assertThat(response).isNotNull();
        log.info("{}->",response);
    }

}