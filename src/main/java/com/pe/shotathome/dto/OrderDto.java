package com.pe.shotathome.dto;

import com.pe.shotathome.entity.Store;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String state;
    private LocalDateTime orderDate;
    private List<OrderLineDto> lines;
    private Double total;
    private Store store;
}
/*
order.setState(ord.getState());
        order.setOrderDate(ord.getOrderDate());
        order.setTotal(ord.getTotal());
        order.setStore(ord.getStore());
*/