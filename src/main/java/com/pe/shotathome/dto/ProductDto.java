package com.pe.shotathome.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private String detail;
    private Long store_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail){
        this.detail=detail;
    }
}