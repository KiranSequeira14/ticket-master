package com.mycompany.ticketmaster.service;

import com.mycompany.ticketmaster.model.Event;
import com.mycompany.ticketmaster.model.Ticket;
import com.mycompany.ticketmaster.model.TicketState;
import com.mycompany.ticketmaster.model.User;
import com.mycompany.ticketmaster.repository.EventRepository;
import com.mycompany.ticketmaster.repository.TicketRepository;
import com.mycompany.ticketmaster.repository.UserRepository;
import com.mycompany.ticketmaster.util.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Ticket> getAvailableTickets(String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        return ticketRepository.findByEventAndState(event, TicketState.AVAILABLE);
    }

    @Transactional
    public void reserveTicket(String eventId, Long userId, Long ticketNumber) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event does not exists!"));
        Ticket ticket = ticketRepository.findByEventAndTicketNumber(event, ticketNumber).orElseThrow(() -> new ResourceNotFoundException("Ticket doesn't exists"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (ticket.getState() != TicketState.AVAILABLE) {
            throw new ResourceNotFoundException("Ticket not available");
        }

        ticket.setState(TicketState.RESERVED);
        ticket.setUser(user);
        ticketRepository.save(ticket);

        // Schedule a task to revert the state if payment is not completed in 10 minutes
        //This could be run as a cronjob for every 10 mins on db OR
        // caching logic can be applied (caching reserved tickets in redis with ttl of 10 mins without updating db entry state
        new Thread(() -> {
            try {
                Thread.sleep(10 * 60 * 1000); // 10 minutes
                if (ticket.getState() == TicketState.RESERVED) {
                    ticket.setState(TicketState.AVAILABLE);
                    ticket.setUser(null);
                    ticketRepository.save(ticket);
                }
            } catch (InterruptedException e) {
                System.out.println("Error" + e.getMessage());
            }
        }).start();


    }

    @Transactional
    public void bookTicket(String eventId, Long userId, Long ticketNumber) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event does not exists!"));
        Ticket ticket = ticketRepository.findByEventAndTicketNumber(event, ticketNumber).orElseThrow(() -> new ResourceNotFoundException("Ticket doesn't exists"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (ticket.getState() != TicketState.RESERVED || !ticket.getUser().getUserId().equals(userId)) {
            throw new IllegalStateException("Ticket cannot be booked");
        }

        ticket.setState(TicketState.BOOKED);
        ticketRepository.save(ticket);
        user.addTicket(ticket);
        userRepository.save(user);

    }
}
