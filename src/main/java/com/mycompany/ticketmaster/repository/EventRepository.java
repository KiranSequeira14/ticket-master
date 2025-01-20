package com.mycompany.ticketmaster.repository;

import com.mycompany.ticketmaster.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
