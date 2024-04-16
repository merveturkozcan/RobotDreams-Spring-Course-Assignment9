package com.robotdreams.assignment9.utils;

import com.robotdreams.assignment9.dto.OrderRequestDto;
import com.robotdreams.assignment9.dto.ProductDetailRequestDto;
import com.robotdreams.assignment9.dto.ProductRequestDto;
import com.robotdreams.assignment9.dto.UserRequestDto;
import com.robotdreams.assignment9.service.OrderService;
import com.robotdreams.assignment9.service.ProductService;
import com.robotdreams.assignment9.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Service
public class UtilTest {


    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    public UtilTest(UserService userService, ProductService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @PostConstruct
    public void saveTestData() {

        // create sample user data
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setFirstname("John");
        userRequestDto.setLastname("Doe");
        userRequestDto.setEmail("john.doe@example.com");
        userRequestDto.setPhoneNumber("+1234567890");
        userRequestDto.setAddress("123 Main Street, Cityville, ST 12345");

        // save sample user date
        userService.save(userRequestDto);

        /*
        // create sample product request dto
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setName("Example Product");
        productRequestDto.setCategory("Electronics");
        productRequestDto.setPhotoUrl("https://example.com/product_photo.jpg");
        productRequestDto.setDescription("This is an example product description.");
        productRequestDto.setPrice(99.99);

        // create sample product detail request dto
        ProductDetailRequestDto productDetailRequestDto = new ProductDetailRequestDto();
        productDetailRequestDto.setProductInfo("test");
        productDetailRequestDto.setProductSerialNumber("merhaba");

        // set product detail of product request dto
        productRequestDto.setProductDetail(productDetailRequestDto);

        // save product
        productService.create(productRequestDto);

        // set order
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setProductIdList(new ArrayList<>(Arrays.asList(1l)));
        orderRequestDto.setOrderNumber(UUID.randomUUID().toString());
        orderRequestDto.setDescription("deneme");
        orderRequestDto.setUserId(1l);

        // save order
        orderService.save(orderRequestDto);
        */
    }

}
