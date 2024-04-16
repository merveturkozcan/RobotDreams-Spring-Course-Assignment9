package com.robotdreams.assignment9.controller;


import com.robotdreams.assignment9.dto.ProductRequestDto;
import com.robotdreams.assignment9.dto.ProductResponseDto;
import com.robotdreams.assignment9.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public String create(@RequestBody ProductRequestDto productRequestDto) {
        return productService.create(productRequestDto)
                ? "Successfully Created!"
                : "An unexpected error occured!";
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        var productResponseDtos = productService.findAll();

        if (productResponseDtos.isPresent())
            return ResponseEntity.ok(productResponseDtos.get());

        return ResponseEntity.notFound().build();
    }
}