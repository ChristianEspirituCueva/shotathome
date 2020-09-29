package com.pe.shotathome.repository;

import com.pe.shotathome.entity.OrderLines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLines,Long> {
}
