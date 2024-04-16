package com.robotdreams.assignment9.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Table(name = "ProductDetails")
@Entity
public class ProductDetail extends BaseEntity implements Serializable {
    private String productInfo;
    private String productSerialNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
