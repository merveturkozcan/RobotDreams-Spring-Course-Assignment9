package com.robotdreams.assignment9.repository;

import com.robotdreams.assignment9.entity.OrderProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {

}
