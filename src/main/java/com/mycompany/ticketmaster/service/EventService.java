package com.mycompany.ticketmaster.service;

import com.mycompany.ticketmaster.DTO.CreateEventRequest;
import com.mycompany.ticketmaster.model.Event;
import com.mycompany.ticketmaster.model.Ticket;
import com.mycompany.ticketmaster.repository.EventRepository;
import com.mycompany.ticketmaster.repository.TicketRepository;
import com.mycompany.ticketmaster.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getByEventId(String eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event Not found!"));
    }

    public Event createEvent(CreateEventRequest request) {
        Event event = new Event();
        event.setPerformerName(request.getPerformerName());
        event.setDateTime(request.getTime());
        event.setLocation(request.getLocation());
        List<Ticket> tickets = new ArrayList<>();
        event.setEventName(request.getEventName());
        for (int i = 1; i <= request.getNumberOfTickets(); i++) {
            Ticket ticket = new Ticket();
            ticket.setTicketNumber(10000L + i);
            ticket.setEvent(event);
            tickets.add(ticket);
        }
        event.setTickets(tickets);
        eventRepository.save(event);
        ticketRepository.saveAll(tickets);
        return event;
    }
}
