package com.robotdreams.assignment9.service;

import com.robotdreams.assignment9.entity.Order;
import com.robotdreams.assignment9.entity.OrderProduct;
import com.robotdreams.assignment9.entity.Product;
import com.robotdreams.assignment9.repository.OrderProductRepository;
import com.robotdreams.assignment9.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;
    private final ProductService productService;

    public OrderProductService(OrderProductRepository orderProductRepository, ProductRepository productRepository, ProductService productService) {
        this.orderProductRepository = orderProductRepository;
        this.productService = productService;
    }

    public void saveAll(List<Product> products, Order order) {
        var orderProducts = products.stream()
                .map(p -> {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setProduct(p);
                    orderProduct.setOrder(order);
                    return orderProduct;
                }).toList();

        orderProductRepository.saveAll(orderProducts);

    }
}
