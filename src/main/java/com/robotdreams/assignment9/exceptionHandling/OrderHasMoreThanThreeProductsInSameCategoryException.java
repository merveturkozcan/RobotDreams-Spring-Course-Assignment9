package com.robotdreams.assignment9.exceptionHandling;

public class OrderHasMoreThanThreeProductsInSameCategoryException extends BusinessException {
    public OrderHasMoreThanThreeProductsInSameCategoryException(String message) {

        // bir sipariş içerisinde tek bir kategoriden en fazla üç ürün yer alabilir şeklinde bir business kuralı getiriyoruz.
        // burada bu kuralı ihlal eden categoryleri consturctor parametresi olarak hatanın oluştuğu yerde istemek, ve o şekilde
        // fırlatmak doğru mu olur yoksa yalnızca "bir sipariş en fazla aynı kategoriden en fazla 3 ürün içerir" şeklinde
        // genel bir mesaj ile mi ilerlemek gerekir ?
        super("An order must include a maximum of 3 products from a single category. Violations: " + message);
    }
}
