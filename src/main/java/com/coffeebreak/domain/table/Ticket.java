package com.coffeebreak.domain.table;


import com.coffeebreak.domain.table.dto.TicketDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus ticketStatus;
    private String startService;
    private String endService;

    public Ticket(TicketDTO dto){
        this.id = dto.id();
        this.ticketStatus = dto.ticketStatus();
        this.startService = dto.startService();
        this.endService = dto.endService();
    }

}
