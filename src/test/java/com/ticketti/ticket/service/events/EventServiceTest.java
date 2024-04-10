package com.ticketti.ticket.service.events;

import com.ticketti.ticket.data.model.Category;
import com.ticketti.ticket.dtos.request.EventCreationRequest;
import com.ticketti.ticket.dtos.response.EventCreationResponse;
import com.ticketti.ticket.exception.TicketException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class EventServiceTest {
    @Autowired
    private EventService eventService;


    @Test
    void test_A_Registered_User_Can_Create_Events() throws TicketException {


        EventCreationRequest request = new EventCreationRequest();
        request.setName("she code africa");
        request.setDescription("a talk show about technology");
        request.setAttendeesCount(100);
        LocalDateTime eventDate = LocalDateTime.of(2024, 4, 10, 15, 30, 0);
        request.setDateTime(eventDate);
        request.setCategory(Category.CONFERENCE);

        EventCreationResponse response = eventService.createEvent(request, 1L);


        assertThat(response).isNotNull();
    }


}