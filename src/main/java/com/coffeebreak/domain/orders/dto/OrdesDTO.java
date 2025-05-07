package com.coffeebreak.domain.orders.dto;

import com.coffeebreak.domain.orders.Orders;
import com.coffeebreak.domain.orders.StatusOrders;
import com.coffeebreak.domain.product.Product;

import java.util.List;

public record OrdesDTO(
        Long id,
        Long ticketId,
        List<Long> productIds,  // <-- novo campo
        List <Long> amount,
        String observation,
        StatusOrders statusOrders
) {
    public OrdesDTO(Orders orders) {
        this(
                orders.getId(),
                orders.getTicket() != null ? orders.getTicket().getId() : null,
                orders.getProduct() != null
                        ? orders.getProduct().stream().map(Product::getId).toList()
                        : List.of(),
                orders.getAmount(),
                orders.getObservation(),
                orders.getStatusOrders()
        );
    }
}
