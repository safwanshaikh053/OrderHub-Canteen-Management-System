package com.canteen.canteen_backend.controller;

import com.canteen.canteen_backend.dto.LoginRequestDTO;
import com.canteen.canteen_backend.service.AdminService;
import com.canteen.canteen_backend.service.CustomerService;
import com.canteen.canteen_backend.service.StaffService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AdminService adminService;
    private final StaffService staffService;
    private final CustomerService customerService;

    public AuthController(AdminService adminService,
                          StaffService staffService,
                          CustomerService customerService) {
        this.adminService = adminService;
        this.staffService = staffService;
        this.customerService = customerService;
    }

    // ============================
    // UNIFIED LOGIN
    // ============================
    @PostMapping("/login")
    public Object login(@RequestBody LoginRequestDTO request) {

        if (request.getRole() == null ||
            request.getEmail() == null ||
            request.getPassword() == null) {
            throw new RuntimeException("Role, email and password are required");
        }

        switch (request.getRole().toUpperCase()) {

            case "ADMIN":
                return adminService.login(
                        request.getEmail(),
                        request.getPassword()
                );

            case "STAFF":
                return staffService.login(
                        request.getEmail(),
                        request.getPassword()
                );

            case "CUSTOMER":
                return customerService.login(
                        request.getEmail(),
                        request.getPassword()
                );

            default:
                throw new RuntimeException("Invalid role: " + request.getRole());
        }
    }
}
