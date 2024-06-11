package com.example.products.models;

import com.example.products.dtos.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity(name="product")
@Table(name="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="uuid")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String name;
    private Double price;
    private String imageURL;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Order> orders;

    public Product(ProductRequestDTO productRequestDTO, String imageURL) {
        this.name = productRequestDTO.name();
        this.price = productRequestDTO.price();
        this.imageURL = imageURL;
    }
}
