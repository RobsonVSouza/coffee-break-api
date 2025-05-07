package com.coffeebreak.domain.orders;

import com.coffeebreak.domain.orders.dto.OrdesDTO;
import com.coffeebreak.domain.product.Product;
import com.coffeebreak.domain.product.ProductRepository;
import com.coffeebreak.domain.product.ProductService;
import com.coffeebreak.domain.product.dto.ProductDTO;
import com.coffeebreak.domain.ticket.Ticket;
import com.coffeebreak.domain.ticket.TicketRepository;
import com.coffeebreak.domain.ticket.TicketService;
import com.coffeebreak.domain.ticket.TicketStatus;
import com.coffeebreak.domain.ticket.dto.TicketDTO;
import com.coffeebreak.exception.ProductNotFoundException;
import com.coffeebreak.exception.TicketException;
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
    ProductService productService;

    @Autowired
    TicketService ticketService   ;

    public Orders save(OrdesDTO dto) {
        TicketDTO ticketDto = ticketService.findById(dto.ticketId());
        Ticket ticket = new Ticket(ticketDto);

        ticketService.verifyTableIsOccupied(ticketDto);

        List<Product> incomingProducts = dto.productIds().stream()
                .map(productService::findById)
                .map(Product::new)
                .toList();

        Optional<Orders> optionalOrders = ordesRepository.findByTicketId(dto.ticketId());

        if (optionalOrders.isPresent()) {
            Orders order = optionalOrders.get();


            for (int i = 0; i < incomingProducts.size(); i++) {
                Product incomingProduct = incomingProducts.get(i);
                Long incomingQuantity = dto.amount().get(i);

                int existingIndex = -1;
                for (int j = 0; j < order.getProduct().size(); j++) {
                    if (order.getProduct().get(j).getId().equals(incomingProduct.getId())) {
                        existingIndex = j;
                        break;
                    }
                }

                if (existingIndex != -1) {

                    Long updatedQuantity = order.getAmount().get(existingIndex) + incomingQuantity;
                    order.getAmount().set(existingIndex, updatedQuantity);
                } else {

                    order.getProduct().add(incomingProduct);
                    order.getAmount().add(incomingQuantity);
                }
            }

            return ordesRepository.save(order);
        } else {
            Orders order = new Orders(dto, ticket, incomingProducts);
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
        TicketDTO ticket = ticketService.findById(dto.ticketId());

        List<Product> products = dto.productIds().stream()
                .map(productService::findById)
                .map(Product::new)
                .toList();

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
