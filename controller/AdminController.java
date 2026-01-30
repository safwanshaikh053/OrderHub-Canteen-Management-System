package com.canteen.canteen_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.canteen.canteen_backend.entity.Admin;
import com.canteen.canteen_backend.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // GET – All admins
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    // GET – Admin by ID
    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Integer id) {
        return adminService.getAdminById(id);
    }

    // POST – Register admin
    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    // PUT – Update admin
    @PutMapping("/{id}")
    public Admin updateAdmin(
            @PathVariable Integer id,
            @RequestBody Admin updatedAdmin) {
        return adminService.updateAdmin(id, updatedAdmin);
    }

    // PATCH – Toggle isActive
    @PatchMapping("/{id}")
    public Admin toggleAdminStatus(@PathVariable Integer id) {
        return adminService.toggleAdminStatus(id);
    }

    // DELETE – Remove admin
    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
    }

    // ✅ SIMPLE LOGIN (EARLIER VERSION)
    @PostMapping("/login")
    public Admin login(
            @RequestParam String email,
            @RequestParam String password) {

        return adminService.login(email, password);
    }
}
