package com.example.products.services;

import com.example.products.dtos.OrderRequestDTO;
import com.example.products.enums.PaymentStatusEnum;
import com.example.products.exceptions.ProductNotFoundException;
import com.example.products.exceptions.ProductSoldException;
import com.example.products.models.Order;
import com.example.products.models.Product;
import com.example.products.repositories.OrderRepository;
import com.example.products.repositories.ProductRepository;
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


    UUID randomUUID;
    String clientEmail;
    Product product;
    Order order;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        randomUUID = UUID.randomUUID();
        clientEmail = "email@email.com";
        product = new Product(randomUUID, "iPhone", 100.00, "");
        order = new Order(clientEmail, PaymentStatusEnum.PENDENTE, product);
    }

    @Test
    @DisplayName("Should create a new order.")
    void createOrder() {
        when(orderRepository.save(order)).thenReturn(order);
        when(productRepository.findById(randomUUID)).thenReturn(Optional.of(product));

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(product.getUuid(), clientEmail);
        Order createdOrder = orderService.createOrder(orderRequestDTO);

        verify(orderRepository, times(1)).save(createdOrder);
        verify(productRepository, times(1)).findById(randomUUID);
    }


    @Test
    @DisplayName("Should except not finding a Product.")
    void exceptProductNotFound() {
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(product.getUuid(), clientEmail);
        try{
            orderService.createOrder(orderRequestDTO);
            fail();
        } catch (ProductNotFoundException productNotFoundException){
            assertEquals("Product not found.", productNotFoundException.getMessage());
        }
    }

    @Test
    @DisplayName("Should except while trying to buy a sold product.")
    void exceptProductSold() {
        when(orderRepository.findByProductUuid(randomUUID)).thenReturn(Optional.of(order));
        when(productRepository.findById(randomUUID)).thenReturn(Optional.of(product));

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(product.getUuid(), clientEmail);
        try{
            orderService.createOrder(orderRequestDTO);
            fail();
        } catch (ProductSoldException productSoldException){
            assertEquals("Product has already been sold.", productSoldException.getMessage());
        }
    }
}