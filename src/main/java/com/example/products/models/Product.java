package com.example.products.models;

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

}
