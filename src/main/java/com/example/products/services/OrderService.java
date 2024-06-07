package com.example.products.services;

import com.example.products.dtos.EmailRequestDTO;
import com.example.products.dtos.OrderRequestDTO;
import com.example.products.enums.PaymentStatusEnum;
import com.example.products.exceptions.ProductNotFoundException;
import com.example.products.exceptions.ProductSoldException;
import com.example.products.models.Order;
import com.example.products.models.Product;
import com.example.products.repositories.OrderRepository;
import com.example.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EmailService emailService;

    public List<Order> getAll(){ return orderRepository.findAll(); }

    public List<Order> getByEmail(String email){ return orderRepository.findByClientEmail(email); }

    public Order createOrder(OrderRequestDTO orderRequestDTO){

        Product product = productRepository.findById(orderRequestDTO.productId()).orElseThrow(ProductNotFoundException::new);
        if (orderRepository.findByProductUuid(product.getUuid()).isPresent()){
            throw new ProductSoldException();
        }

        Order order = new Order(orderRequestDTO.clientEmail(), PaymentStatusEnum.PENDENTE, product);

        String subject = "Seu pedido foi realizado";
        String body = "Parabéns, seu " + product.getName() + " está aguardando o pagamento de R$" + product.getPrice() + ",00 \n" + product.getImageURL();
        EmailRequestDTO emailRequestDTO = new EmailRequestDTO(orderRequestDTO.clientEmail(), subject, body);

        emailService.sendEmail(emailRequestDTO);
        return orderRepository.save(order);
    }
}
