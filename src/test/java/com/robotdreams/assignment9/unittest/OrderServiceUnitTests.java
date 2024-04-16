package com.robotdreams.assignment9.unittest;

import com.robotdreams.assignment9.dto.ProductRequestDto;
import com.robotdreams.assignment9.entity.Product;
import com.robotdreams.assignment9.entity.ProductDetail;
import com.robotdreams.assignment9.exceptionHandling.InsufficientOrderAmountException;
import com.robotdreams.assignment9.exceptionHandling.OrderHasMoreThanThreeProductsInSameCategoryException;
import com.robotdreams.assignment9.repository.OrderRepository;
import com.robotdreams.assignment9.service.OrderProductService;
import com.robotdreams.assignment9.service.OrderService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTests {

    @InjectMocks
    private OrderService orderService;

    @Test
    public void checkIfOrderAmountIsSufficent_ShouldThrowInsufficientOrderAmountException_WhenTotalAmountIsBelow50() {

        Product product1 = Mockito.mock(Product.class);
        Product product2 = Mockito.mock(Product.class);

        double product1Price = 1.0;
        double product2Price = 5.0;

        List<Product> products = List.of(product1, product2);

        Mockito.when(product1.getPrice()).thenReturn(product1Price);
        Mockito.when(product2.getPrice()).thenReturn(product2Price);

        Exception exception = assertThrows(InsufficientOrderAmountException.class, () ->
                orderService.checkIfOrderAmountIsSufficent(products));

        String expectedMessage = "The order amount of " + (product1Price + product2Price) + " is lower than the minimum order amount.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void checkIfOrderAmountIsSufficent_WhenTotalPriceIsGreaterThanMinOrderTotalPrice()
            throws InsufficientOrderAmountException {

        Product product1 = Mockito.mock(Product.class);
        Product product2 = Mockito.mock(Product.class);

        double product1Price = 100.0;
        double product2Price = 200.0;

        List<Product> products = List.of(product1, product2);

        Mockito.when(product1.getPrice()).thenReturn(product1Price);
        Mockito.when(product2.getPrice()).thenReturn(product2Price);

        orderService.checkIfOrderAmountIsSufficent(products);
    }

    @Test
    public void checkIfOrderHasMoreThanThreeProductsInSameCategory_WhenOrderIncludesMoreThanThreeProductInASingleCategory()
            throws OrderHasMoreThanThreeProductsInSameCategoryException {

        Product product1InCategory1 = Mockito.mock(Product.class);
        Product product2InCategory1 = Mockito.mock(Product.class);
        Product product3InCategory1 = Mockito.mock(Product.class);
        Product product4InCategory1 = Mockito.mock(Product.class);

        Product product1InCategory2 = Mockito.mock(Product.class);
        Product product2InCategory2 = Mockito.mock(Product.class);
        Product product3InCategory2 = Mockito.mock(Product.class);
        Product product4InCategory2 = Mockito.mock(Product.class);

        Mockito.when(product1InCategory1.getCategory()).thenReturn("category1");
        Mockito.when(product2InCategory1.getCategory()).thenReturn("category1");
        Mockito.when(product3InCategory1.getCategory()).thenReturn("category1");
        Mockito.when(product4InCategory1.getCategory()).thenReturn("category1");

        Mockito.when(product1InCategory2.getCategory()).thenReturn("category2");
        Mockito.when(product2InCategory2.getCategory()).thenReturn("category2");
        Mockito.when(product3InCategory2.getCategory()).thenReturn("category2");
        Mockito.when(product4InCategory2.getCategory()).thenReturn("category2");

        List<Product> products = List.of(
                product1InCategory1, product2InCategory1, product3InCategory1, product4InCategory1,
                product1InCategory2, product2InCategory2, product3InCategory2, product4InCategory2);

        Exception exception = assertThrows(OrderHasMoreThanThreeProductsInSameCategoryException.class, () -> {
            orderService.checkIfOrderHasMoreThanThreeProductsInSameCategory(products);
        });

        String expectedMessage = "An order must include a maximum of 3 products from a single category. Violations: category2 : 4 category1 : 4";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


    @Test
    public void checkIfOrderHasMoreThanThreeProductsInSameCategory_WhenOrderDoesNotIncludeMoreThanThreeProductInASingleCategory()
            throws OrderHasMoreThanThreeProductsInSameCategoryException {

        Product product1InCategory1 = Mockito.mock(Product.class);
        Product product2InCategory1 = Mockito.mock(Product.class);

        Product product1InCategory2 = Mockito.mock(Product.class);
        Product product2InCategory2 = Mockito.mock(Product.class);

        Mockito.when(product1InCategory1.getCategory()).thenReturn("category1");
        Mockito.when(product2InCategory1.getCategory()).thenReturn("category1");

        Mockito.when(product1InCategory2.getCategory()).thenReturn("category2");
        Mockito.when(product2InCategory2.getCategory()).thenReturn("category2");

        List<Product> products = List.of(
                product1InCategory1, product2InCategory1,
                product1InCategory2, product2InCategory2);

        orderService.checkIfOrderHasMoreThanThreeProductsInSameCategory(products);
    }
}
