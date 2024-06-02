package com.example.products.services;

import com.example.products.dtos.ProductRequestDTO;
import com.example.products.models.Product;
import com.example.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product createProduct(ProductRequestDTO productRequestDTO){
        Product newProduct = new Product(productRequestDTO);
        return productRepository.save(newProduct);
    }
}
