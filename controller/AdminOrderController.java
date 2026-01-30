package com.canteen.canteen_backend.controller;

import com.canteen.canteen_backend.entity.Order;
import com.canteen.canteen_backend.entity.OrderStatus;
import com.canteen.canteen_backend.repository.OrderRepository;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
@CrossOrigin
public class AdminOrderController {

    private final OrderRepository orderRepository;

    public AdminOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // ============================
    // GET ALL ORDERS (ADMIN)
    // ============================
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // ============================
    // GET ORDER DETAILS
    // ============================
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));
    }

    // ============================
    // UPDATE ORDER STATUS (ADMIN)
    // ============================
    @PatchMapping("/{id}/status")
    public Order updateOrderStatus(
            @PathVariable Integer id,
            @RequestParam OrderStatus status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        // timestamps handling
        if (status == OrderStatus.CONFIRMED) {
            order.setConfirmedAt(new Timestamp(System.currentTimeMillis()));
        }
        if (status == OrderStatus.PREPARING) {
            order.setPreparingAt(new Timestamp(System.currentTimeMillis()));
        }
        if (status == OrderStatus.READY) {
            order.setReadyAt(new Timestamp(System.currentTimeMillis()));
        }

        order.setStatus(status);
        return orderRepository.save(order);
    }

    // ============================
    // CANCEL ORDER (ADMIN)
    // ============================
    @PatchMapping("/{id}/cancel")
    public Order cancelOrder(@PathVariable Integer id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }
}
