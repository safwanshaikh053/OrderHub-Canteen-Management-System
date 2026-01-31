package com.canteen.canteen_backend.service;
import org.springframework.web.multipart.MultipartFile;

import com.canteen.canteen_backend.dto.MenuItemCreateRequest;
import com.canteen.canteen_backend.entity.Admin;
import com.canteen.canteen_backend.entity.Category;
import com.canteen.canteen_backend.entity.MenuItem;
import com.canteen.canteen_backend.repository.AdminRepository;
import com.canteen.canteen_backend.repository.CategoryRepository;
import com.canteen.canteen_backend.repository.MenuItemRepository;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class AdminMenuService {

    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;
    private final AdminRepository adminRepository;

    public AdminMenuService(
            MenuItemRepository menuItemRepository,
            CategoryRepository categoryRepository,
            AdminRepository adminRepository) {

        this.menuItemRepository = menuItemRepository;
        this.categoryRepository = categoryRepository;
        this.adminRepository = adminRepository;
    }

    // ============================
    // CREATE MENU ITEM
    // ============================
    public MenuItem createMenuItem(MenuItemCreateRequest request) {

        if (request.getItemName() == null || request.getItemName().isBlank()) {
            throw new IllegalArgumentException("Item name is required");
        }

        if (request.getPrice() == null || request.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid category"));

        Admin admin = adminRepository.findById(request.getAdminId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid admin"));

        MenuItem item = new MenuItem();
        item.setItemName(request.getItemName());
        item.setPrice(request.getPrice());
        item.setCategory(category);
        item.setAdmin(admin);
        item.setIsVeg(request.getIsVeg());
        item.setAvailability(request.getAvailability());

        return menuItemRepository.save(item);
    }

    // ============================
    // GET ALL MENU ITEMS (ADMIN)
    // ============================
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }
    
    public MenuItem createMenuItem1(MenuItemCreateRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category"));

        Admin admin = adminRepository.findById(request.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin"));

        MenuItem item = new MenuItem();
        item.setItemName(request.getItemName());
        item.setPrice(request.getPrice());
        item.setCategory(category);
        item.setAdmin(admin);
        item.setAvailability(request.getAvailability());
        item.setIsVeg(request.getIsVeg());

        return menuItemRepository.save(item);
    }


}
