package com.canteen.canteen_backend.repository;

import com.canteen.canteen_backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
