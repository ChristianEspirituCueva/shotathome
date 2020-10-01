package com.pe.shotathome.controller;



import com.pe.shotathome.entity.Order;
import com.pe.shotathome.exception.ResourceNotFoundException;
import com.pe.shotathome.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public Page<Order> getOrders(Pageable pageable) {
        return OrderRepository.findAll(pageable);
    }


    @PostMapping("/orders")
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PutMapping("/orders/{orderId}")
    public Order updateOrder(@PathVariable Long orderId,
                                   @Valid @RequestBody Order orderRequest) {
        return orderRepository.findById(orderId)
                .map(order -> {
                    order.setTitle(orderRequest.getTitle());
                    order.setDescription(orderRequest.getDescription());
                    return orderRepository.save(order);
                }).orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
    }


    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        return orderRepository.findById(orderId)
                .map(order -> {
                    orderRepository.delete(order);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
    }






}