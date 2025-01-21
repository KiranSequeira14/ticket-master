package com.mycompany.ticketmaster.controller;

import com.mycompany.ticketmaster.DTO.CreateEventRequest;
import com.mycompany.ticketmaster.model.Event;
import com.mycompany.ticketmaster.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return ResponseEntity.ok(eventService.getByEventId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Event> addEvent(@RequestBody CreateEventRequest request) {
        Event event = eventService.createEvent(request);
        return ResponseEntity.ok(event);
    }
}
