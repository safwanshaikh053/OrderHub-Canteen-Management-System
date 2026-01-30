package com.canteen.canteen_backend.controller;

import com.canteen.canteen_backend.dto.LoginRequest_old;
import com.canteen.canteen_backend.entity.Staff;
import com.canteen.canteen_backend.service.StaffService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin
public class StaffAuthController {

    private final StaffService staffService;

    public StaffAuthController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/login")
    public Staff login(@RequestBody LoginRequest_old req) {
        return staffService.login(req.getEmail(), req.getPassword());
    }
}
