package com.canteen.canteen_backend.repository;

import com.canteen.canteen_backend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    // Get all items for a given order
    List<OrderItem> findByOrderOrderId(Integer orderId);

    // (Optional) Get items assigned to a staff member
    List<OrderItem> findByStaffStaffId(Integer staffId);
}
