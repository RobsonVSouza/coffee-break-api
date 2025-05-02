package com.coffeebreak.domain.ticket;

import com.coffeebreak.domain.ticket.dto.TicketDTO;
import com.coffeebreak.exception.ProductNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.coffeebreak.domain.ticket.TicketStatus.BUSY;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public Ticket save(TicketDTO dto){
    Ticket  ticketData = new Ticket(dto);
    return ticketRepository.save(ticketData);
    }

    public List<TicketDTO> findAll(){
        return ticketRepository.findAll()
                .stream()
                .map(TicketDTO::new)
                .toList();
    }

    public TicketDTO findById(Long id){
        return ticketRepository.findById(id)
                .map(TicketDTO::new)
                .orElseThrow(()-> new ProductNotFoundException("Mesa com Id " + id + " não encontrado"));
    }

    public TicketDTO update(Long id, TicketDTO ticketDTO){
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Mesa com Id " + id + " não encontrado"));

        BeanUtils.copyProperties(ticketDTO, existingTicket, "id");
        return new TicketDTO(ticketRepository.save(existingTicket));
    }

    public void delete(Long id){
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Mesa com Id " + id + " não encontrado"));

        if (ticket.getTicketStatus() == BUSY){
            throw new ProductNotFoundException("Mesa está ocupada não pode ser deletada");
        }
         ticketRepository.deleteById(id);
    }

}
