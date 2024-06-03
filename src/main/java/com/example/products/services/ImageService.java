package com.example.products.services;

import com.example.products.config.FeignSuportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "image-service", url = "http://localhost:8081/api/image", configuration = FeignSuportConfig.class)
public interface ImageService {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadImage(@RequestParam("file") MultipartFile file);
}
