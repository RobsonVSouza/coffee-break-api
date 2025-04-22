package com.coffeebreak.domain.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusOrders {

    ORDER_PLACED("Pedido feito"),
    BEING_PREPARED("Sendo preparado"),
    READY("Pronto"),
    DELIVERED("Entregue"),
    CANCELLED("Cancelado");

    private final String name;

}
