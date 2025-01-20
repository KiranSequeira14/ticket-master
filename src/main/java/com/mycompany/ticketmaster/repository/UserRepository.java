package com.mycompany.ticketmaster.repository;

import com.mycompany.ticketmaster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
