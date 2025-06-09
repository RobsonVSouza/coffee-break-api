package com.coffeebreak.domain.orders;

import com.coffeebreak.domain.cart.OrderItem;
import com.coffeebreak.domain.orders.dto.OrdesDTO;
import com.coffeebreak.domain.product.Product;
import com.coffeebreak.domain.product.ProductService;
import com.coffeebreak.domain.ticket.Ticket;
import com.coffeebreak.domain.ticket.TicketService;
import com.coffeebreak.domain.ticket.dto.TicketDTO;
import com.coffeebreak.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    @Autowired
    OrdesRepository ordesRepository;

    @Autowired
    ProductService productService;

    @Autowired
    TicketService ticketService;

    public Orders save(OrdesDTO dto) {
        TicketDTO ticketDto = ticketService.findById(dto.ticketId());
        Ticket ticket = new Ticket(ticketDto);

        ticketService.verifyTableIsOccupied(ticketDto);

        List<OrderItem> incomingOrderItems = dto.items().stream()
                .map(itemDto -> {
                    Product product = new Product(productService.findById(itemDto.product().id()));
                    return new OrderItem(null, product, itemDto.quantity());
                })
                .collect(Collectors.toList());

        Optional<Orders> optionalOrders = ordesRepository.findByTicketId(dto.ticketId());

        if (optionalOrders.isPresent()) {
            Orders order = optionalOrders.get();

            for (OrderItem incomingItem : incomingOrderItems) {
                boolean found = false;
                for (OrderItem existingItem : order.getItems()) {
                    if (existingItem.getProduct().getId().equals(incomingItem.getProduct().getId())) {
                        existingItem.setQuantity(existingItem.getQuantity() + incomingItem.getQuantity());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    order.getItems().add(incomingItem);
                }
            }
            order.setObservation(dto.observation());
            order.setStatusOrders(dto.statusOrders() != null ? dto.statusOrders() : order.getStatusOrders());

            return ordesRepository.save(order);
        } else {
            Orders order = new Orders(dto, ticket, incomingOrderItems);
            return ordesRepository.save(order);
        }
    }

    public List<OrdesDTO> findAll(){
        return ordesRepository.findAll()
                .stream()
                .map(OrdesDTO::new)
                .toList();
    }

    public OrdesDTO findById(Long id) {
        return ordesRepository.findById(id)
                .map(OrdesDTO::new)
                .orElseThrow(() -> new ProductNotFoundException("Não encontrado a conta"));
    }

    public OrdesDTO update(Long id, OrdesDTO dto){
        Orders existingOrder = ordesRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Comanda não encontrada"));

        TicketDTO ticketDto = ticketService.findById(dto.ticketId());
        Ticket ticket = new Ticket(ticketDto);

        existingOrder.setTicket(ticket);
        existingOrder.setObservation(dto.observation());
        existingOrder.setStatusOrders(dto.statusOrders());

        existingOrder.getItems().clear();
        dto.items().forEach(itemDto -> {
            Product product = new Product(productService.findById(itemDto.product().id()));
            existingOrder.getItems().add(new OrderItem(null, product, itemDto.quantity()));
        });

        return new OrdesDTO(ordesRepository.save(existingOrder));
    }

    public void delete(Long id){
        findById(id);
        ordesRepository.deleteById(id);
    }

}