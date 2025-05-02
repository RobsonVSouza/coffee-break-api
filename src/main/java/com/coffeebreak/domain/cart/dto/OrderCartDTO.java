package com.coffeebreak.domain.cart.dto;

import com.coffeebreak.domain.cart.OrderCart;
import com.coffeebreak.domain.orders.Orders;

import java.math.BigDecimal;
import java.util.List;

public record OrderCartDTO (Long id, Long ticketId, List<Orders> ordersList, BigDecimal totalPrice) {

    public OrderCartDTO(OrderCart orderCart){
        this(
                orderCart.getId(),
                orderCart.getTicket() != null ? orderCart.getTicket().getId() : null,
                orderCart.getOrdersList() != null ? orderCart.getOrdersList() : null,
                orderCart.getTotalPrice()
        );
    }
}
