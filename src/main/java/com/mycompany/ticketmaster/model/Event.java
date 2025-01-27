package com.mycompany.ticketmaster.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mycompany.ticketmaster.util.EventIdGenerator;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String eventId;
    private String EventName;
    private String performerName; //Could be linked to a performer entity later
    private LocalDateTime dateTime;
    private String location;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Ticket> tickets;

    @PrePersist
    public void generateEventId() {
        this.eventId = EventIdGenerator.generateEventId();
    }
}
