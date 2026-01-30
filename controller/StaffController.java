package com.canteen.canteen_backend.controller;

import com.canteen.canteen_backend.dto.LoginRequest_old;
import com.canteen.canteen_backend.entity.Staff;
import com.canteen.canteen_backend.service.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/staff")
@CrossOrigin
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // ✅ GET – All staff
    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    // ✅ GET – Staff by ID (FIXED PATH)
    @GetMapping("/by-id/{id}")
    public Staff getStaffById(@PathVariable Integer id) {
        return staffService.getStaffById(id);
    }

    // ✅ POST – Create staff
    @PostMapping
    public Staff createStaff(@RequestBody Staff staff) {
        return staffService.createStaff(staff);
    }

    // ✅ POST – Staff login (NO CONFLICT NOW)
    @PostMapping("/login")
    public Staff login(@RequestBody LoginRequest_old req) {
        return staffService.login(req.getEmail(), req.getPassword());
    }
}
