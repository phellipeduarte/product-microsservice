package com.example.products.services;

import com.example.products.dtos.EmailRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-service", url = "${env.email.url}")
public interface EmailService {

    @PostMapping()
    void sendEmail(@RequestBody EmailRequestDTO emailRequest);
}
