package com.canteen.canteen_backend.controller;

import com.canteen.canteen_backend.dto.MenuItemCreateRequest;
import com.canteen.canteen_backend.entity.MenuItem;
import com.canteen.canteen_backend.service.AdminMenuService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/menu-items")
@CrossOrigin
public class AdminMenuController {

    private final AdminMenuService adminMenuService;

    public AdminMenuController(AdminMenuService adminMenuService) {
        this.adminMenuService = adminMenuService;
    }

    // ✅ ADD MENU ITEM
    @PostMapping
    public MenuItem createMenuItem(
            @RequestBody MenuItemCreateRequest request) {

        return adminMenuService.createMenuItem(request);
    }

    // ✅ GET ALL MENU ITEMS
    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return adminMenuService.getAllMenuItems();
    }
}
