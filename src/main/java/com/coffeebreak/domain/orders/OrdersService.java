package com.coffeebreak.domain.orders;

import com.coffeebreak.domain.orders.dto.OrdesDTO;
import com.coffeebreak.domain.product.Product;
import com.coffeebreak.domain.product.ProductRepository;
import com.coffeebreak.domain.ticket.Ticket;
import com.coffeebreak.domain.ticket.TicketRepository;
import com.coffeebreak.domain.ticket.TicketStatus;
import com.coffeebreak.exception.ProductNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .orElseThrow(() -> new ProductNotFoundException("Ticket não encontrado"));

        if (ticket.getTicketStatus() == TicketStatus.AVAILABLE){
            throw new ProductNotFoundException("Mesa precisa estar ocupada");
        }
        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        Optional<Orders> optionalOrders = ordesRepository.findByTicketIdAndProductId(dto.ticketId(), dto.productId());

        if (optionalOrders.isPresent()) {
            Orders order = optionalOrders.get();
            order.setAmount(order.getAmount() + dto.amount());
            return ordesRepository.save(order);
        } else {
            Orders order = new Orders(dto, ticket, product);
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
        Ticket ticket = ticketRepository.findById(dto.ticketId())
                .orElseThrow(() -> new ProductNotFoundException("Ticket não encontrado"));

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        Orders existOrders = ordesRepository.findById(id)
                        .orElseThrow(() -> new ProductNotFoundException("Comanda não encontrada"));

        BeanUtils.copyProperties(dto, existOrders, "id");
        return new OrdesDTO(ordesRepository.save(existOrders));
    }

    public void delete(Long id){
        findById(id);
        ordesRepository.deleteById(id);
    }

}
