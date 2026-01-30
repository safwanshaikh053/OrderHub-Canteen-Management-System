package com.canteen.canteen_backend.controller;

import com.canteen.canteen_backend.entity.Payment;
import com.canteen.canteen_backend.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;

    // ✅ Manual constructor
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // ✅ POST – Make payment for an order
    @PostMapping("/order/{orderId}")
    public Payment makePayment(
            @PathVariable Integer orderId,
            @RequestBody Payment payment) {

        return paymentService.makePayment(orderId, payment);
    }

    // ✅ GET – Get payment by ID
    @GetMapping("/{paymentId}")
    public Payment getPaymentById(@PathVariable Integer paymentId) {
        return paymentService.getPaymentById(paymentId);
    }
}
