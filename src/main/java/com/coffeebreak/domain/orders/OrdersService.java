package com.coffeebreak.domain.orders;

import com.coffeebreak.domain.orders.dto.OrdesDTO;
import com.coffeebreak.domain.product.Product;
import com.coffeebreak.domain.product.ProductRepository;
import com.coffeebreak.domain.table.Ticket;
import com.coffeebreak.domain.table.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    OrdesRepository ordesRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TicketRepository ticketRepository   ;

    public Orders save(OrdesDTO dto) {
        Ticket ticket = ticketRepository.findById(dto.ticketId())
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Orders order = new Orders();
        order.setTicket(ticket);
        order.setProduct(product);
        order.setAmount(dto.amount());
        order.setObservation(dto.observation());
        order.setStatusOrders(dto.statusOrders());

        return ordesRepository.save(order);
    }

}
