package com.example.products.repositories;

import com.example.products.dtos.OrderRequestDTO;
import com.example.products.dtos.ProductRequestDTO;
import com.example.products.enums.PaymentStatusEnum;
import com.example.products.models.Order;
import com.example.products.models.Product;
import jakarta.persistence.EntityManager;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;



import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get order successfully.")
    void shouldFindOrderByClientEmail(){
        String clientEmail = "email@email.com";
        ProductRequestDTO product = new ProductRequestDTO("iPhone", 1000.00);
        this.createOrder(clientEmail, this.createProduct(product));

        List<Order> result = this.orderRepository.findByClientEmail(clientEmail);
        assertEquals(result.get(0).getClientEmail(), clientEmail);
    }

    @Test
    @DisplayName("Should not return any order.")
    void shouldNotFindOrderByClientEmail(){
        String clientEmail = "email@email.com";
        List<Order> result = this.orderRepository.findByClientEmail(clientEmail);
        assertTrue(result.isEmpty());
    }


    private Product createProduct(ProductRequestDTO productRequestDTO){
        Product newProduct = new Product(productRequestDTO, "");
        this.entityManager.persist(newProduct);
        return newProduct;
    }

    private Order createOrder(String clientEmail, Product product){
        Order newOrder = new Order(clientEmail, PaymentStatusEnum.PENDENTE, product);
        this.entityManager.persist(newOrder);
        return newOrder;
    }
}