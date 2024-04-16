package com.robotdreams.assignment9.dto;

import java.io.Serializable;

public class ProductDetailRequestDto implements Serializable {
    private String productInfo;
    private String productSerialNumber;

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getProductSerialNumber() {
        return productSerialNumber;
    }

    public void setProductSerialNumber(String productSerialNumber) {
        this.productSerialNumber = productSerialNumber;
    }
}
