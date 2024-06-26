package com.ticketti.ticket.service.events;

import com.ticketti.ticket.data.model.Category;
import com.ticketti.ticket.data.model.Event;
import com.ticketti.ticket.dtos.request.CancelReservationRequest;
import com.ticketti.ticket.dtos.request.EventCreationRequest;
import com.ticketti.ticket.dtos.request.ReserveTicketRequest;
import com.ticketti.ticket.dtos.request.SearchEventRequest;
import com.ticketti.ticket.dtos.response.*;
import com.ticketti.ticket.exception.TicketException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Slf4j
class EventServiceTest {
    @Autowired
    private EventService eventService;


    @Test
    void test_A_Registered_User_Can_Create_Events() throws TicketException {


        EventCreationRequest request = new EventCreationRequest();
        request.setName("she can code africa");
        request.setDescription("a talk show about technology");
        request.setAttendeesCount(100);
        LocalDateTime eventDate = LocalDateTime.of(2024, 4, 10, 15, 30, 0);
        request.setDateTime(eventDate);
        request.setCategory("CONFERENCE");

        EventCreationResponse response = eventService.createEvent(request, 1L);


        assertThat(response).isNotNull();
    }

    @Test
    void test_A_Registered_User_Can_Create_Multiple_Events() throws TicketException {


        EventCreationRequest request = new EventCreationRequest();
        request.setName("java techie");
        request.setDescription("a talk show about java");
        request.setAttendeesCount(2);
        LocalDateTime eventDate = LocalDateTime.of(2024, 12, 10, 15, 30, 0);
        request.setDateTime(eventDate);
        request.setCategory("CONFERENCE");

        EventCreationResponse response = eventService.createEvent(request, 1L);
        assertThat(response).isNotNull();
    }

    @Test
    void test_A_Registered_User_Can_Create_Multiple_Events2() throws TicketException {


        EventCreationRequest request = new EventCreationRequest();
        request.setName("kitchen crib");
        request.setDescription("a talk show about food");
        request.setAttendeesCount(200);
        LocalDateTime eventDate = LocalDateTime.of(2024, 1, 10, 15, 30, 0);
        request.setDateTime(eventDate);
        request.setCategory("Concert");

        EventCreationResponse response = eventService.createEvent(request, 1L);
        assertThat(response).isNotNull();
    }
    @Test
    void test_A_Registered_User_Cannot_Create_Event_WIth_Category_That_Does_Not_Exist() throws TicketException {


        EventCreationRequest request = new EventCreationRequest();
        request.setName("Ay");
        request.setDescription("a laugh out loud show");
        request.setAttendeesCount(2000);
        LocalDateTime eventDate = LocalDateTime.of(2024, 6, 10, 15, 30, 0);
        request.setDateTime(eventDate);
        request.setCategory("C");

        assertThrows(TicketException.class,()->eventService.createEvent(request, 1L));

    }


    @Test
    void test_A_Registered_User_Cannot_Create_Events_With_Same_Name()  {


        EventCreationRequest request = new EventCreationRequest();
        request.setName("she can code africa");
        request.setDescription("a talk show about technology");
        request.setAttendeesCount(100);
        LocalDateTime eventDate = LocalDateTime.of(2024, 4, 10, 15, 30, 0);
        request.setDateTime(eventDate);
        request.setCategory("CONFERENCE");

        assertThrows(TicketException.class,()->eventService.createEvent(request, 1L));

    }
    @Test
    void  test_A_Registered_User_Can_Get_List_Of_Events_He_Or_She_Created() throws TicketException {
        UserEventsResponse events = eventService.findUserEvents(1L);
        log.info("{} ->",events);
    }

    @Test
    void test_A_User_Can_Search_For_Event() throws TicketException {
        SearchEventRequest request = new SearchEventRequest();
        request.setEventName("java techie");
        SearchEventResponse response = eventService.searchEvent(request);
        log.info("{}->",response);
        assertThat(response).isNotNull();
    }
    @Test
    void test_That_User_Cannot_Find_Event_That_Does_Not_Exist(){
        SearchEventRequest request = new SearchEventRequest();
        request.setEventName("java");
        assertThrows(TicketException.class,()->eventService.searchEvent(request));
    }


    @Test
    void test_A_Registered_User_Can_Create_Events_2() throws TicketException {

        EventCreationRequest request = new EventCreationRequest();
        request.setName("Gaming");
        request.setDescription("learn basic ways of creating games");
        request.setAttendeesCount(50);
        LocalDateTime eventDate = LocalDateTime.of(2024, 4, 19, 5, 30, 0);
        request.setDateTime(eventDate);
        request.setCategory("GAME");

       EventCreationResponse response = eventService.createEvent(request,2L);
       assertThat(response).isNotNull();

    }

    @Test
    void test_A_Registered_User_Can_Create_Events2() throws TicketException {


        EventCreationRequest request = new EventCreationRequest();
        request.setName("pencil unbroken");
        request.setDescription("live comedy");
        request.setAttendeesCount(1000);
        LocalDateTime eventDate = LocalDateTime.of(2024, 8, 20, 21, 0, 0);
        request.setDateTime(eventDate);
        request.setCategory("CONCERT");

        EventCreationResponse response = eventService.createEvent(request, 2L);


        assertThat(response).isNotNull();
    }




}