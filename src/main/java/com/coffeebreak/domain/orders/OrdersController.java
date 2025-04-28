package com.coffeebreak.domain.orders;

import com.coffeebreak.domain.orders.dto.OrdesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<OrdesDTO>> findAll(){
        List <OrdesDTO> orders = ordersService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity <OrdesDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdesDTO> updateOrders(@PathVariable Long id, @RequestBody OrdesDTO dto){
        OrdesDTO update = ordersService.update(id, dto);
        return ResponseEntity.ok(update);
    }
}
