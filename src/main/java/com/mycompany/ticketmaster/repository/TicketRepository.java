package com.mycompany.ticketmaster.repository;

import com.mycompany.ticketmaster.model.Event;
import com.mycompany.ticketmaster.model.Ticket;
import com.mycompany.ticketmaster.model.TicketState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEventAndState(Event event, TicketState state);

    Optional<Ticket> findByEventAndTicketNumber(Event event, Long ticketNumber);
}
