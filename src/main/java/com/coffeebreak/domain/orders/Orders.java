package com.coffeebreak.domain.orders;

import com.coffeebreak.domain.orders.dto.OrdesDTO;
import com.coffeebreak.domain.product.Product;
import com.coffeebreak.domain.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.coffeebreak.domain.orders.StatusOrders.ORDER_PLACED;

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

    @ManyToMany
    private List<Product> product;

    private List <Long> amount;

    private String observation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrders statusOrders = ORDER_PLACED;

    public Orders(OrdesDTO dto, Ticket ticket, List <Product> product) {
        this.id = dto.id();
        this.ticket = ticket;
        this.product = product;
        this.amount = dto.amount();
        this.observation = dto.observation();
        this.statusOrders = dto.statusOrders() != null ? dto.statusOrders() : ORDER_PLACED;
    }

}
