package com.example.products.controllers;


import com.example.products.dtos.OrderEmailRequestDTO;
import com.example.products.dtos.OrderRequestDTO;
import com.example.products.models.Order;
import com.example.products.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<Order>> getAll(){
        return ResponseEntity.ok().body(orderService.getAll());
    }

    @GetMapping("/email")
    public ResponseEntity getByEmail(@RequestBody OrderEmailRequestDTO orderEmailRequestDTO){
        return ResponseEntity.ok().body(orderService.getByEmail(orderEmailRequestDTO.email()));
    }

    @PostMapping()
    public ResponseEntity createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.ok().body(orderService.createOrder(orderRequestDTO));
    }
}
