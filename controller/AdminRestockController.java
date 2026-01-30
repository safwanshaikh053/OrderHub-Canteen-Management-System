package com.canteen.canteen_backend.controller;

import com.canteen.canteen_backend.dto.RestockRequestAdminDTO;
import com.canteen.canteen_backend.entity.*;
import com.canteen.canteen_backend.repository.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/restock-requests")
@CrossOrigin
public class AdminRestockController {

    private final RestockRequestRepository restockRequestRepository;
    private final DrinksInventoryRepository drinksInventoryRepository;

    public AdminRestockController(
            RestockRequestRepository restockRequestRepository,
            DrinksInventoryRepository drinksInventoryRepository) {

        this.restockRequestRepository = restockRequestRepository;
        this.drinksInventoryRepository = drinksInventoryRepository;
    }

    // ============================
    // GET ALL PENDING REQUESTS
    // ============================
    @GetMapping("/pending")
    public List<RestockRequestAdminDTO> getPendingRequests() {

        return restockRequestRepository.findByStatus(RestockStatus.PENDING)
                .stream()
                .map(r -> new RestockRequestAdminDTO(
                        r.getRequestId(),
                        r.getStaff().getName(),
                        r.getDrink().getDrinkName(),
                        r.getRequestedQuantity(),
                        r.getStatus().name(),
                        r.getNote(),
                        r.getRequestedAt()
                ))
                .toList();
    }

    // ============================
    // APPROVE REQUEST
    // ============================
    @PatchMapping("/{id}/approve")
    public RestockRequest approveRequest(@PathVariable Integer id) {

        RestockRequest request = restockRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getStatus() != RestockStatus.PENDING) {
            throw new RuntimeException("Only pending requests can be approved");
        }

        DrinksInventory drink = request.getDrink();

        // ✅ Update stock
        drink.setCurrentStock(
                drink.getCurrentStock() + request.getRequestedQuantity()
        );
        drink.setLastRestockDate(java.sql.Date.valueOf(LocalDate.now()));


        drinksInventoryRepository.save(drink);

        // ✅ Update request status
        request.setStatus(RestockStatus.APPROVED);
        return restockRequestRepository.save(request);
    }

    // ============================
    // REJECT REQUEST
    // ============================
    @PatchMapping("/{id}/reject")
    public RestockRequest rejectRequest(@PathVariable Integer id) {

        RestockRequest request = restockRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getStatus() != RestockStatus.PENDING) {
            throw new RuntimeException("Only pending requests can be rejected");
        }

        request.setStatus(RestockStatus.REJECTED);
        return restockRequestRepository.save(request);
    }
}
