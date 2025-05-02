package com.coffeebreak.domain.ticket;


import com.coffeebreak.domain.ticket.dto.TicketDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.coffeebreak.domain.ticket.TicketStatus.AVAILABLE;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus = AVAILABLE;
    private String startService;
    private String endService;

    public Ticket(TicketDTO dto){
        this.id = dto.id();
        this.ticketStatus = dto.ticketStatus() != null ? dto.ticketStatus() : TicketStatus.AVAILABLE;
        this.startService = dto.startService();
        this.endService = dto.endService();
    }

}
