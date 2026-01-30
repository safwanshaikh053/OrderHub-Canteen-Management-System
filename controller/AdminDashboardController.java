package com.canteen.canteen_backend.controller;

import org.springframework.web.bind.annotation.*;

import com.canteen.canteen_backend.dto.AdminDashboardStatsDTO;
import com.canteen.canteen_backend.entity.OrderStatus;
import com.canteen.canteen_backend.repository.AdminRepository;
import com.canteen.canteen_backend.repository.MenuItemRepository;
import com.canteen.canteen_backend.repository.OrderRepository;

@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin
public class AdminDashboardController {

    private final AdminRepository adminRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderRepository orderRepository;

    public AdminDashboardController(
            AdminRepository adminRepository,
            MenuItemRepository menuItemRepository,
            OrderRepository orderRepository) {

        this.adminRepository = adminRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderRepository = orderRepository;
    }

    // ============================
    // GET – Dashboard Statistics
    // ============================
    @GetMapping("/stats")
    public AdminDashboardStatsDTO getDashboardStats() {

        long totalAdmins = adminRepository.count();
        long menuItems = menuItemRepository.count();
        long totalOrders = orderRepository.count();
        long pendingOrders = orderRepository.countByStatus(OrderStatus.PENDING);

        double totalRevenue = 0.0; // will calculate later

        return new AdminDashboardStatsDTO(
                totalAdmins,
                menuItems,
                totalOrders,
                pendingOrders,
                totalRevenue
        );
    }
}
