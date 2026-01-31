package com.canteen.canteen_backend.service;

import com.canteen.canteen_backend.entity.Order;
import com.canteen.canteen_backend.entity.Payment;
import com.canteen.canteen_backend.entity.OrderStatus;
import com.canteen.canteen_backend.repository.OrderRepository;
import com.canteen.canteen_backend.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentRepository paymentRepository,
                          OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    // ✅ Make payment for an order
    public Payment makePayment(Integer orderId, Payment payment) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with id: " + orderId));

        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setPaymentStatus("SUCCESS");

        // ✅ Update order status after payment
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);

        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Integer paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found with id: " + paymentId));
    }
}
