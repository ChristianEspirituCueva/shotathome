package com.pe.shotathome.services;

import com.pe.shotathome.entity.Order;
import com.pe.shotathome.entity.OrderLines;
import com.pe.shotathome.entity.Product;
import com.pe.shotathome.exeptions.GeneralServiceException;
import com.pe.shotathome.exeptions.NoDataFoundException;
import com.pe.shotathome.exeptions.ValidateServiceException;
import com.pe.shotathome.repository.OrderLineRepository;
import com.pe.shotathome.repository.OrderRepository;
import com.pe.shotathome.repository.ProductRepository;
import com.pe.shotathome.validators.OrderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Order> findAll(Pageable page){
        try {
            return orderRepository.findAll(page).toList();
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public Order findById(Long id) {
        try {
            return orderRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public void delete(Long id) {
        try {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));

            orderRepository.delete(order);

        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    public Order save(Order order) {
        try {
            OrderValidator.save(order);

            double total = 0;
            for(OrderLines line : order.getLines()) {
                Product product = productRepository.findById(line.getProduct().getId())
                        .orElseThrow(() -> new NoDataFoundException("No existe el producto " + line.getProduct().getId()));

                line.setPrice(product.getPrice());
                line.setTotal(product.getPrice() * line.getQuantity());
                total += line.getTotal();
            }
            order.setTotal(total);
            order.getLines().forEach(line -> line.setOrder(order));

            //Create Order
            if(order.getId() == null) {
                order.setOrderDate(LocalDateTime.now());
                return orderRepository.save(order);
            }

            //Update Order
            Order savedOrder = orderRepository.findById(order.getId())
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));
            //RegDate no se cambia, se mantiene la de creacion
            order.setOrderDate(savedOrder.getOrderDate());

            List<OrderLines> deletedLines = savedOrder.getLines();//Obtiene las lineas asociadas a la order obtenida
            deletedLines.removeAll(order.getLines());//Elimina las lineas asociadas a la orden
            orderLineRepository.deleteAll(deletedLines);

            return orderRepository.save(order);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}
