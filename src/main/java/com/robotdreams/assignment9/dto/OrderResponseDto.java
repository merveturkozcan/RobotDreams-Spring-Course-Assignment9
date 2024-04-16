package com.robotdreams.assignment9.dto;

import java.io.Serializable;

public class OrderResponseDto extends BaseResponseDto implements Serializable {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
