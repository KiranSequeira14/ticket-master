package com.mycompany.ticketmaster.controller;

import com.mycompany.ticketmaster.DTO.TicketRequest;
import com.mycompany.ticketmaster.model.Ticket;
import com.mycompany.ticketmaster.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/{eventId}")
    public List<Ticket> getAllTickets(@PathVariable String eventId) {
        return ticketService.getAvailableTickets(eventId);
    }

    @PostMapping("")
    public void bookTicket(@RequestBody TicketRequest ticketRequest) {
        ticketService.bookTicket(ticketRequest.getEventId(), ticketRequest.getUserId(), ticketRequest.getTicketNumber());
    }
}
