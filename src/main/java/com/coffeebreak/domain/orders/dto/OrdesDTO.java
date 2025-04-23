package com.coffeebreak.domain.orders.dto;

import com.coffeebreak.domain.orders.Orders;
import com.coffeebreak.domain.orders.StatusOrders;

public record OrdesDTO (Long id, Long ticketId, Long productId, Long amount, String observation, StatusOrders statusOrders) {

    public OrdesDTO(Orders orders) {
        this(
                orders.getId(),
                orders.getTicket() != null ? orders.getTicket().getId() : null,
                orders.getProduct() != null ? orders.getProduct().getId() : null,
                orders.getAmount(),
                orders.getObservation(),
                orders.getStatusOrders()
        );
    }
}
