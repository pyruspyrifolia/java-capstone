package com.ascendient.daamsrv.repositories;

import com.ascendient.daamsrv.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserid(Long userid);
}