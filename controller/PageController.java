package com.canteen.canteen_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    // ---------- REGISTER ----------
    @GetMapping("/register")
    public String unifiedRegisterPage() {
        return "register";
    }

    @GetMapping("/register/admin")
    public String registerAdmin() {
        return "register-admin";
    }

    @GetMapping("/register/staff")
    public String registerStaff() {
        return "register-staff";
    }

    @GetMapping("/register/customer")
    public String registerCustomer() {
        return "register-customer";
    }

    // ---------- LOGIN ----------
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // ---------- DASHBOARDS ----------
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/staff/dashboard")
    public String staffDashboard() {
        return "staff/dashboard"; // Points to templates/staff/dashboard.html
    }

    @GetMapping("/customer/dashboard")
    public String customerDashboard() {
        return "customer-dashboard";
    }

    // ---------- ADDITIONAL STAFF ROUTES ----------
    @GetMapping("/staff/orders")
    public String staffOrders() {
        return "staff/orders";
    }

    @GetMapping("/staff/inventory")
    public String staffInventory() {
        return "staff/inventory";
    }
}