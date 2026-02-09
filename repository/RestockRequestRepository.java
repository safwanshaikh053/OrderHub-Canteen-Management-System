package com.canteen.canteen_backend.repository;

import com.canteen.canteen_backend.entity.RestockRequest;
import com.canteen.canteen_backend.entity.RestockStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestockRequestRepository
        extends JpaRepository<RestockRequest, Integer> {

    // =========================
    // ADMIN USE CASES
    // =========================

    // Get all pending restock requests
    List<RestockRequest> findByStatus(RestockStatus string);

    // =========================
    // STAFF USE CASES
    // =========================

    // Get all restock requests raised by a staff member
    List<RestockRequest> findByStaffStaffId(Integer staffId);
}
