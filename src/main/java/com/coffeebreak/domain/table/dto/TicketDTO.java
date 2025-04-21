package com.coffeebreak.domain.table.dto;

import com.coffeebreak.domain.table.Ticket;
import com.coffeebreak.domain.table.TicketStatus;

public record TicketDTO(Long id, TicketStatus ticketStatus, String startService, String endService) {

    public TicketDTO(Ticket ticket){
        this(ticket.getId(),ticket.getTicketStatus(),ticket.getStartService(),ticket.getEndService());
    }


}
