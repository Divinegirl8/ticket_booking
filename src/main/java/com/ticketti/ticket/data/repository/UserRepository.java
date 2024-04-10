package com.ticketti.ticket.data.repository;

import com.ticketti.ticket.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByName(String name);
    User findUserByEmail(String email);
}
