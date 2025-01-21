package com.mycompany.ticketmaster.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateEventRequest {
    private String eventName;
    private String performerName;
    private LocalDateTime time;
    private String location;
    private int numberOfTickets;

}
