package com.example.products.models;

import com.example.products.dtos.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name="product")
@Table(name="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String name;
    private Double price;
    private String imageURL;

    public Product(ProductRequestDTO productRequestDTO) {
        this.name = productRequestDTO.name();
        this.price = productRequestDTO.price();
        this.imageURL = productRequestDTO.imageURL();
    }
}
