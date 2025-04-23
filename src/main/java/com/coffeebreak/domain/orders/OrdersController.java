package com.coffeebreak.domain.orders;

import com.coffeebreak.domain.orders.dto.OrdesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


    @PostMapping
    public ResponseEntity<OrdesDTO> createOrder(@RequestBody OrdesDTO dto) {
        Orders order = ordersService.save(dto);
        return ResponseEntity.ok(new OrdesDTO(order));
    }
}
