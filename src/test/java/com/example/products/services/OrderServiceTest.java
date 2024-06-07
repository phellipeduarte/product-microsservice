package com.example.products.services;

import com.example.products.dtos.EmailRequestDTO;
import com.example.products.dtos.OrderRequestDTO;
import com.example.products.enums.PaymentStatusEnum;
import com.example.products.models.Order;
import com.example.products.models.Product;
import com.example.products.repositories.OrderRepository;
import com.example.products.repositories.ProductRepository;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    EmailService emailService;

    @Autowired
    @InjectMocks
    OrderService orderService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a new order.")
    void createOrder() {

        UUID randomUUID = UUID.randomUUID();
        String clientEmail = "email@email.com";

        Product product = new Product(randomUUID, "iPhone", 100.00, "");
        Order order = new Order(clientEmail, PaymentStatusEnum.PENDENTE, product);

        when(orderRepository.save(order)).thenReturn(order);
        when(productRepository.findById(randomUUID)).thenReturn(Optional.of(product));

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(product.getUuid(), clientEmail);
        Order createdOrder = orderService.createOrder(orderRequestDTO);

        verify(orderRepository, times(1)).save(createdOrder);
        verify(productRepository, times(1)).findById(randomUUID);
    }
}