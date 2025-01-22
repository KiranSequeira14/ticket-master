package com.mycompany.ticketmaster.repository;

import com.mycompany.ticketmaster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String emailId);
}
