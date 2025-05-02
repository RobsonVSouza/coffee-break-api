package com.coffeebreak.domain.cart;

import com.coffeebreak.domain.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderCartRepository extends JpaRepository <OrderCart, Long> {
    Optional<OrderCart> findByTicket(Ticket ticket);
}
