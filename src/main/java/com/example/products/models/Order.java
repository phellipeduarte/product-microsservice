package com.example.products.models;

import com.example.products.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name="orders")
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="uuid")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String clientEmail;

    private PaymentStatusEnum status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    public Order(String clientEmail, PaymentStatusEnum status, Product product) {
        this.clientEmail = clientEmail;
        this.status = status;
        this.product = product;
    }
}
