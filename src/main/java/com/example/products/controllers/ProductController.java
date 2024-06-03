package com.example.products.controllers;

import com.example.products.dtos.ProductRequestDTO;
import com.example.products.models.Product;
import com.example.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok().body(productService.getAll());
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity createProduct(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("name") String name,
            @RequestParam("price") String price){
            ProductRequestDTO productRequestDTO = new ProductRequestDTO(name, Double.valueOf(price));
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequestDTO, multipartFile));
    }
}
