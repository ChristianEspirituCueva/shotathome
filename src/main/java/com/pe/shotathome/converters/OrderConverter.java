package com.pe.shotathome.converters;

import com.pe.shotathome.dto.OrderDto;
import com.pe.shotathome.entity.Order;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderConverter {
    public static Order DtoToEntity(OrderDto ord) {
        Order order=new Order();
        order.setState(ord.getState());
        order.setOrderDate(ord.getOrderDate());
        order.setTotal(ord.getTotal());
        order.setStore(ord.getStore());
        return order;
    }
    public static OrderDto EntityToDto(Order ord) {
        OrderDto orderDto=new OrderDto();
        orderDto.setState(ord.getState());
        orderDto.setOrderDate(ord.getOrderDate());
        orderDto.setTotal(ord.getTotal());
        orderDto.setStore(ord.getStore());
        return orderDto;
    }
}
