package com.pe.shotathome.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDto {
    private Long id;
    private ProductDto product;
    private Double price;
    private Double quantity;
    private Double total;
}