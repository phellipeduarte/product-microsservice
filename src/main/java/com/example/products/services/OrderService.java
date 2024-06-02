package com.example.products.services;

import com.example.products.enums.PaymentStatusEnum;
import com.example.products.exceptions.ProductNotFoundException;
import com.example.products.models.Order;
import com.example.products.models.Product;
import com.example.products.repositories.OrderRepository;
import com.example.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Order> getAll(){ return orderRepository.findAll(); }

    public List<Order> getByEmail(String email){ return orderRepository.findByClientEmail(email); }

    public Order createOrder(UUID productUUID, String clientEmail){
        Product product = productRepository.findById(productUUID).orElseThrow(ProductNotFoundException::new);
        Order order = new Order(clientEmail, PaymentStatusEnum.PENDENTE, product);
        return order;
    }
}
