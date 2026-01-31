package com.canteen.canteen_backend.service;

import com.canteen.canteen_backend.dto.OrderItemRequest;
import com.canteen.canteen_backend.dto.OrderRequest;
import com.canteen.canteen_backend.dto.OrderTimelineResponse;
import com.canteen.canteen_backend.entity.*;
import com.canteen.canteen_backend.repository.*;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuItemRepository menuItemRepository;
    private final CustomerRepository customerRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            MenuItemRepository menuItemRepository,
            CustomerRepository customerRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.menuItemRepository = menuItemRepository;
        this.customerRepository = customerRepository;
    }

    // =========================
    // PLACE ORDER (DINE-IN ONLY)
    // =========================
    @Transactional
    public Order placeOrder(Integer customerId, OrderRequest request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderType("DINE_IN");
        order.setStatus(OrderStatus.PENDING);
        order.setSpecialInstructions(request.getSpecialInstructions());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));

        order = orderRepository.save(order);

        double totalAmount = 0;

        for (OrderItemRequest itemReq : request.getItems()) {

            MenuItem menuItem = menuItemRepository.findById(itemReq.getMenuItemId())
                    .orElseThrow(() -> new RuntimeException("Menu item not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemReq.getQuantity());
            orderItem.setUnitPrice(menuItem.getPrice());

            totalAmount += menuItem.getPrice() * itemReq.getQuantity();

            orderItemRepository.save(orderItem);
        }

        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }

    // =========================
    // CUSTOMER ORDERS
    // =========================
    public List<Order> getOrdersByCustomerId(Integer customerId) {
        return orderRepository.findByCustomerCustomerId(customerId);
    }

    // =========================
    // GET ORDER BY ID
    // =========================
    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // =========================
    // UPDATE ORDER
    // =========================
    public Order updateOrder(Integer orderId, Order updatedOrder) {

        Order existing = getOrderById(orderId);

        existing.setStatus(updatedOrder.getStatus());
        existing.setSpecialInstructions(updatedOrder.getSpecialInstructions());

        return orderRepository.save(existing);
    }

    // =========================
    // CANCEL ORDER
    // =========================
    public Order cancelOrder(Integer orderId) {

        Order order = getOrderById(orderId);
        order.setStatus(OrderStatus.CANCELLED);

        return orderRepository.save(order);
    }

    // =========================
    // ORDER TIMELINE
    // =========================
    public OrderTimelineResponse getOrderTimeline(Integer orderId) {

        Order order = getOrderById(orderId);

        return new OrderTimelineResponse(
                order.getOrderId(),
                order.getStatus().name(),   // ✅ String
                order.getOrderDate(),       // placedAt
                order.getConfirmedAt(),
                order.getPreparingAt(),
                order.getReadyAt(),
                null      // ✅ added
        );
    }

}
