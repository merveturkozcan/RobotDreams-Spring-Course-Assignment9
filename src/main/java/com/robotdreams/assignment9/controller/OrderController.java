package com.robotdreams.assignment9.controller;


import com.robotdreams.assignment9.dto.OrderRequestDto;
import com.robotdreams.assignment9.dto.OrderResponseDto;
import com.robotdreams.assignment9.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody OrderRequestDto orderRequestDto) {

        orderService.save(orderRequestDto);
        return new ResponseEntity(HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAll() {

        var orderResponseDtos = orderService.findAll();

        if (orderResponseDtos.isPresent())
            return ResponseEntity.ok(orderResponseDtos.get());

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public void delete(@RequestParam long orderId) {
        orderService.delete(orderId);
    }
}
