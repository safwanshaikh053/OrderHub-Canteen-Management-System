package com.canteen.canteen_backend.controller;

import com.canteen.canteen_backend.entity.Order;
import com.canteen.canteen_backend.service.OrderService;
import org.springframework.web.bind.annotation.*;
import com.canteen.canteen_backend.dto.OrderTimelineResponse;

import java.util.List;

@RestController
@RequestMapping("/api/customer/orders")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ✅ POST – Place order
    @PostMapping("/{customerId}")
    public Order placeOrder(
            @PathVariable Integer customerId,
            @RequestBody Order order) {

        return orderService.placeOrder(customerId, order);
    }

    // ✅ GET – Orders of a customer
    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomer(@PathVariable Integer customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    // ✅ GET – Order by ID
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Integer orderId) {
        return orderService.getOrderById(orderId);
    }

    // ✅ PUT – Update order
    @PutMapping("/{orderId}")
    public Order updateOrder(
            @PathVariable Integer orderId,
            @RequestBody Order updatedOrder) {

        return orderService.updateOrder(orderId, updatedOrder);
    }

    // ✅ PATCH – Cancel order
    @PatchMapping("/{orderId}/cancel")
    public Order cancelOrder(@PathVariable Integer orderId) {
        return orderService.cancelOrder(orderId);
    }
    
 // ✅ LIVE ORDER TRACKING TIMELINE
    @GetMapping("/{orderId}/timeline")
    public OrderTimelineResponse trackOrder(@PathVariable Integer orderId) {
        return orderService.getOrderTimeline(orderId);
    }
}
