package com.example.products.services;

import com.example.products.dtos.ProductRequestDTO;
import com.example.products.models.Product;
import com.example.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageService imageService;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product createProduct(ProductRequestDTO productRequestDTO, MultipartFile file){
        String url = imageService.uploadImage(file);
        Product newProduct = new Product(productRequestDTO, url);
        return productRepository.save(newProduct);
    }
}
