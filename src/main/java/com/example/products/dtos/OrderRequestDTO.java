package com.example.products.dtos;

import java.util.UUID;

public record OrderRequestDTO(UUID productId, String clientEmail) {
}
