package com.robotdreams.assignment9.service;

import com.robotdreams.assignment9.dto.ProductRequestDto;
import com.robotdreams.assignment9.dto.ProductResponseDto;
import com.robotdreams.assignment9.entity.Product;
import com.robotdreams.assignment9.exceptionHandling.GeneralException;
import com.robotdreams.assignment9.exceptionHandling.InvalidPriceException;
import com.robotdreams.assignment9.repository.ProductRepository;
import com.robotdreams.assignment9.service.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class    ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository productRepository
            , ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public boolean create(ProductRequestDto createProductRequestDto) {
        Product product = mapper.mapToProduct(createProductRequestDto);

        // dto üzerinde price alanının sıfırdan büyük olup olmadığını kontrol etmek için servis katmanına kadar gelmek
        // yerine bazı input validationlar ile request üzerinden doğrudan kontrol yapıp bad request dönebilir miyiz ?

        // bu tür bir kontrol yapmak istesek best practice nasıl olurdu?

        try {
            checkIfPriceIsValid(createProductRequestDto.getPrice());
        } catch (InvalidPriceException e) {
            System.out.println("Logged!");
            throw new GeneralException("Invalid price");
        }
        return productRepository.save(product).getId() > 0;
    }

    public Optional<List<ProductResponseDto>> findAll() {
        var responseDtos = productRepository.findAll().stream()
                .map(mapper::productToProductResponseDto).toList();

        return Optional.of(responseDtos);
    }

    private void checkIfPriceIsValid(double price)
            throws InvalidPriceException {

        if(price <= 0.0)
            throw new InvalidPriceException("The price must be greater than 0. The input was: " + price);
    }

    public Product findById(long id) {
        return productRepository.findById(id).get();
    }
}
