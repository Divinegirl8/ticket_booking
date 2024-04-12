package com.ticketti.ticket.service.user;

import com.ticketti.ticket.data.model.User;
import com.ticketti.ticket.data.repository.UserRepository;
import com.ticketti.ticket.dtos.request.UserRegistrationRequest;
import com.ticketti.ticket.dtos.response.UserRegistrationResponse;
import com.ticketti.ticket.exception.TicketException;
import com.ticketti.ticket.service.user.validation.Validate;
import com.ticketti.ticket.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ticketti.ticket.service.user.validation.Validate.*;
import static com.ticketti.ticket.util.Mapper.mapUser;

@Service
@AllArgsConstructor
public class UserServiceApp implements UserService{
    private  final UserRepository userRepository;


    @Override
    public UserRegistrationResponse register(UserRegistrationRequest request) throws TicketException {
        validateUsername(request.getName());
        validateEmail(request.getEmail());
        validatePassword(request.getPassword());
        validateUserExist(request);


        User user = mapUser(request.getName(),request.getEmail(),request.getPassword());
        userRepository.save(user);

        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setId(user.getId());

        return response;
    }

    private void validateUserExist(UserRegistrationRequest request) throws TicketException {
        if (checkIfEmailExist(request.getEmail())){
            throw new TicketException("user with that email exists, kindly provide another emil address");
        }
    }

    public  boolean checkIfEmailExist(String email){
        User user = userRepository.findUserByEmail(email);

        return user != null;
    }
}
