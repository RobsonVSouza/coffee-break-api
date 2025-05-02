package com.coffeebreak.domain.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TicketStatus {

    AVAILABLE("available", "disponivel"),
    BUSY("busy", "ocupada");

    private final String name;
    private final  String description;
}
