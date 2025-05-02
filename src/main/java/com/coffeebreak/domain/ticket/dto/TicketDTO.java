package com.coffeebreak.domain.ticket.dto;

import com.coffeebreak.domain.ticket.Ticket;
import com.coffeebreak.domain.ticket.TicketStatus;

public record TicketDTO(Long id, TicketStatus ticketStatus, String startService, String endService) {

    public TicketDTO(Ticket ticket){
        this(ticket.getId(),ticket.getTicketStatus(),ticket.getStartService(),ticket.getEndService());
    }


}
