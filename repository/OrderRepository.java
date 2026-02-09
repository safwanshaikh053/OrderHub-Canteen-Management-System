package com.canteen.canteen_backend.repository;

import com.canteen.canteen_backend.entity.Order;
import com.canteen.canteen_backend.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCustomerCustomerId(Integer customerId);

    // ✅ ONLY orders that need auto update
    List<Order> findByStatusIn(List<OrderStatus> statuses);
    
    long countByStatus(OrderStatus status);
    List<Order> findByStatus(OrderStatus status);

}