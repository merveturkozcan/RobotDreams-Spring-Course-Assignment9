package com.robotdreams.assignment9.repository;


import com.robotdreams.assignment9.entity.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ListCrudRepository<Product, Long> {

}