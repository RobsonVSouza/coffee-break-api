package com.coffeebreak.domain.orders.dto;

import com.coffeebreak.domain.cart.dto.OrderItemDTO;
import com.coffeebreak.domain.orders.Orders;
import com.coffeebreak.domain.orders.StatusOrders;
import com.coffeebreak.domain.product.Product;

import java.util.List;
import java.util.stream.Collectors;

public record OrdesDTO(
        Long id,
        Long ticketId,
        List<OrderItemDTO> items,
        String observation,
        StatusOrders statusOrders
) {
    public OrdesDTO(Orders orders) {
        this(
                orders.getId(),
                orders.getTicket() != null ? orders.getTicket().getId() : null,
                orders.getItems() != null
                        ? orders.getItems().stream().map(item -> new OrderItemDTO(item.getId(), new com.coffeebreak.domain.product.dto.ProductDTO(item.getProduct()), item.getQuantity(), item.getProduct().getPrice().multiply(java.math.BigDecimal.valueOf(item.getQuantity())))).collect(Collectors.toList())
                        : List.of(),
                orders.getObservation(),
                orders.getStatusOrders()
        );
    }
}