package com.ticketti.ticket.service.user;

import com.ticketti.ticket.dtos.request.UserRegistrationRequest;
import com.ticketti.ticket.dtos.response.UserRegistrationResponse;
import com.ticketti.ticket.exception.TicketException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void test_That_A_User_Can_Register() throws TicketException {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setName("Divine");
        request.setEmail("Divine@gmail.com");
        request.setPassword("password@1");
        UserRegistrationResponse response = userService.register(request);
        assertThat(response).isNotNull();
    }

    @Test
    void test_That_A_UserName_Throws_Exception_If_Length_Is_Greater_Than_100(){
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setName("Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine Divine");
        assertThrows(TicketException.class,()->userService.register(request));
    }

    @Test
    void test_That_A_User_Cannot_Register_With_Same_Email_Address(){
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setEmail("Divine@gmail.com");
        assertThrows(TicketException.class,()->userService.register(request));
    }
    @Test
    void test_That_A_User_Cannot_Register_With_A_Password_Less_Than_8_Characters(){
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setPassword("pass");
        assertThrows(TicketException.class,()->userService.register(request));
    }

}