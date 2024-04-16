package com.robotdreams.assignment9.dto;

import java.io.Serializable;

public class ProductRequestDto implements Serializable {
    private String name;
    private String category;
    private String photoUrl;
    private String description;
    private Double price;

    private ProductDetailRequestDto productDetail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductDetailRequestDto getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetailRequestDto productDetail) {
        this.productDetail = productDetail;
    }
}
