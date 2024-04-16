package com.robotdreams.assignment9.dto;

import java.io.Serializable;
import java.util.List;

public class OrderRequestDto implements Serializable {
    private List<Long> productIdList;
    private String description;
    private Long userId;
    private String orderNumber;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<Long> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<Long> productIdList) {
        this.productIdList = productIdList;
    }
}
