package com.coffeebreak.domain.cart;

import com.coffeebreak.domain.orders.Orders;
import com.coffeebreak.domain.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderCart {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Ticket ticket;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Orders> ordersList;

    private BigDecimal totalPrice;


}
