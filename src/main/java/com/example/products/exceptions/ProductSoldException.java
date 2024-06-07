package com.example.products.exceptions;

public class ProductSoldException extends RuntimeException {

    public ProductSoldException() {
        super("Product has already been sold.");
    }
}
