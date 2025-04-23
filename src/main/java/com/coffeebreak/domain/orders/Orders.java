package com.coffeebreak.domain.orders;

import com.coffeebreak.domain.orders.dto.OrdesDTO;
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

    @ManyToOne
    private Ticket ticket;

    @ManyToOne
    private Product product;

    private Long amount;

    private String observation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrders statusOrders;

    public Orders(OrdesDTO dto, Ticket ticket, Product product) {
        this.id = dto.id();
        this.ticket = ticket;
        this.product = product;
        this.amount = dto.amount();
        this.observation = dto.observation();
        this.statusOrders = dto.statusOrders();
    }

}
