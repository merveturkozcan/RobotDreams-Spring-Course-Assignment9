package com.robotdreams.assignment9.service;

import com.robotdreams.assignment9.dto.OrderRequestDto;
import com.robotdreams.assignment9.entity.Order;
import com.robotdreams.assignment9.entity.Product;
import com.robotdreams.assignment9.entity.User;
import com.robotdreams.assignment9.exceptionHandling.GeneralException;
import com.robotdreams.assignment9.exceptionHandling.InsufficientOrderAmountException;
import com.robotdreams.assignment9.exceptionHandling.OrderHasMoreThanThreeProductsInSameCategoryException;
import com.robotdreams.assignment9.repository.OrderRepository;
import com.robotdreams.assignment9.service.mapper.OrderMapper;
import com.robotdreams.assignment9.dto.OrderResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final ProductService productService;
    private final OrderProductService orderProductService;
    private final SmsService smsService;
    private final UserService userService;


    public OrderService(OrderRepository orderRepository, OrderMapper mapper, ProductService productService, OrderProductService orderProductService, SmsService smsService, UserService userService) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
        this.productService = productService;
        this.orderProductService = orderProductService;
        this.smsService = smsService;
        this.userService = userService;
    }

    public void save(OrderRequestDto orderRequestDto) {

        List<Long> productIds = orderRequestDto.getProductIdList();

        // persist order
        Order order = mapper.OrderRequestDtoToOrder(orderRequestDto);
        orderRepository.save(order);

        // get products
        List<Product> products = productIds.stream()
                .map(productService::findById)
                .toList();


        // Sorularım - 1

        // Eğer ki daha fazla business methodu gelirse aşağıdaki kısım oldukça büyüyecek
        // o sebepten aşağıdaki kod bloğu git gide uzayarak bu methodu büyütecek , bu methodun bu şekilde büyümesini engellemenin yolu ne olabilir
        // (Controller Advice kullanmanın dışında, aşağıdaki kodu refactor etmenin bir yolu olabilir mi?

        try {
            checkIfOrderAmountIsSufficent(products);
            checkIfOrderHasMoreThanThreeProductsInSameCategory(products);
        } catch (InsufficientOrderAmountException e) {
            System.out.println("Insufficent Order Amount Logged !");
            throw new GeneralException("The order amount is insufficient ");
        } catch (OrderHasMoreThanThreeProductsInSameCategoryException e) {
            System.out.println("More Than 3 Product In the Same Category Logged !");
            throw new GeneralException("An order must include a maximum of 3 products from a single category.");
        }

        // persist orderProducts
        orderProductService.saveAll(products, order);

        // find user with ID
        User user = userService.findById(orderRequestDto.getUserId()).get();

        // send SMS
        smsService.sendSms(order, user);

    }

    // Sorularım - 2

    // bu method ile birlikte bir sonraki methodu da statik yapmalı mıyım, herhangi bir instance variable a erişmiyorlar diye düşündüm

    // c# ile kod yazarken bu tür durumlarda IDE, örneğin; bu method herhangi bir instace variable a erişmiyor, statik yapabilirsin şeklinde öneri veriyordu
    // bunu takip etmeli miyiz (private static şeklinde belirtmek mantıklı olur mu?) test açısından erişim sağlamak için test esnasında public yapmamız gerekecek gibi ama)


    // Sorularım - 3
    // bu satırdan sonra yer alan 2 method (business rule kontrolü yapan methodlar)
    // doğrudan Order entitysinde yer alabilir mi?
    // entitylerde bu tür iş mantıklarına yer vermeli miyiz ?
    public void checkIfOrderAmountIsSufficent(List<Product> products)
            throws InsufficientOrderAmountException {

        double minOrderAmount = 50.0;

        var total = products.stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum);

        if (minOrderAmount > total)
            throw new InsufficientOrderAmountException(total);
    }

    public void checkIfOrderHasMoreThanThreeProductsInSameCategory(List<Product> products)
            throws OrderHasMoreThanThreeProductsInSameCategoryException {

        // Business kuralı: bir sipariş aynı kategoriden en fazla üç ürün içerebilir

        Map<String, Long> categoryProductCount = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));

        StringBuilder message = new StringBuilder();

        // üçten fazla aynı kategoriden olan ürün varsa isimlerini bir string içerisinde topluyorum
        categoryProductCount.forEach((category, count) -> {
            if (count > 3)
                message.append(category)
                        .append(" : ")
                        .append(count)
                        .append(" ");
        });

        String resultMessage = message.toString();

        // Sorularım - 4

        // üçten fazla ürün içeren kategori isimlerini constructor a geçiyoruz, böylece hata mesajı oluşturulmuş olacak
        // exception mesajının business kuralını ihlal eden kategorileri ve bu kategorilere karşılık gelen product sayılarını (exception ın sebebini)
        // göstermesi uygun mudur. Yoksa genellikle yalnızca düz metin şeklinde "Sipariş tek bir kategoriden max 3 adet ürün içerecek şekilde geçilebilir"
        // şeklinde bir hata mesajı kullanırız?
        if (!message.isEmpty())
            throw new OrderHasMoreThanThreeProductsInSameCategoryException(resultMessage.trim());
    }

    public Optional<List<OrderResponseDto>> findAll() {
        var responseDtos = StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .map(mapper::orderToOrderResponseDto)
                .toList();

        return Optional.of(responseDtos);
    }

    public void delete(long orderId) {
        orderRepository.deleteById(orderId);
    }
}
