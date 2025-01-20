package com.mycompany.ticketmaster.service;

import com.mycompany.ticketmaster.model.Event;
import com.mycompany.ticketmaster.repository.EventRepository;
import com.mycompany.ticketmaster.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getByEventId(String eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event Not found!"));
    }
}
