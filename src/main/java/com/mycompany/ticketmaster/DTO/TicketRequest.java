package com.mycompany.ticketmaster.DTO;

import lombok.Data;

@Data
public class TicketRequest {
    private String eventId;
    private Long userId;
    private Long ticketNumber;
}
