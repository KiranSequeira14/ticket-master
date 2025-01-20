package com.mycompany.ticketmaster.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    private Long ticketNumber;
    private TicketState state = TicketState.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "event_id" )
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
