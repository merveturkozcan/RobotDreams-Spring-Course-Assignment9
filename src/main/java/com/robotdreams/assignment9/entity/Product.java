package com.robotdreams.assignment9.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Table(name = "Products")
@Entity
public class Product extends BaseEntity implements Serializable {
    private String name;
    private String category;
    private String photoUrl;
    private String description;
    private Double price;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ProductDetail productDetail;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<OrderProduct> orderProducts;

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

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }
}
