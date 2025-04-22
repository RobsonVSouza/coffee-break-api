package com.coffeebreak.domain.orders;

import com.coffeebreak.domain.product.Product;
import com.coffeebreak.domain.table.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Ticket ticketId;

    private Product productId;

    private Long amount;

    private String observation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrders statusOrders;
}
