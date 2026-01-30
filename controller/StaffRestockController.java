package com.canteen.canteen_backend.controller;

import com.canteen.canteen_backend.dto.RestockRequestCreateDTO;
import com.canteen.canteen_backend.entity.*;
import com.canteen.canteen_backend.repository.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff/restock-requests")
@CrossOrigin
public class StaffRestockController {
	

    private final RestockRequestRepository restockRequestRepository;
    private final StaffRepository staffRepository;
    private final DrinksInventoryRepository drinksInventoryRepository;

    public StaffRestockController(
            RestockRequestRepository restockRequestRepository,
            StaffRepository staffRepository,
            DrinksInventoryRepository drinksInventoryRepository) {

        this.restockRequestRepository = restockRequestRepository;
        this.staffRepository = staffRepository;
        this.drinksInventoryRepository = drinksInventoryRepository;
    }

    // ✅ ONLY THIS METHOD SHOULD EXIST
    @PostMapping
    public RestockRequest createRestockRequest(
            @RequestBody RestockRequestCreateDTO dto) {

        Staff staff = staffRepository.findById(dto.getStaffId())
                .orElseThrow(() ->
                        new RuntimeException("Staff not found with id: " + dto.getStaffId()));

        DrinksInventory drink = drinksInventoryRepository.findById(dto.getDrinkId())
                .orElseThrow(() ->
                        new RuntimeException("Drink not found with id: " + dto.getDrinkId()));

        if (dto.getRequestedQuantity() == null || dto.getRequestedQuantity() <= 0) {
            throw new RuntimeException("Requested quantity must be greater than 0");
        }

        RestockRequest request = new RestockRequest();
        request.setStaff(staff);
        request.setDrink(drink);
        request.setRequestedQuantity(dto.getRequestedQuantity());
        request.setNote(dto.getNote());
        request.setStatus(RestockStatus.PENDING);

        return restockRequestRepository.save(request);
    }
}

