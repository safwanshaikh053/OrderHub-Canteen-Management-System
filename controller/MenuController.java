package com.canteen.canteen_backend.controller;

import com.canteen.canteen_backend.entity.MenuItem;
import com.canteen.canteen_backend.repository.MenuItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin
public class MenuController {

    private final MenuItemRepository menuItemRepository;

    public MenuController(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    // ✅ GET – All menu items
    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    // ✅ GET – Only available items
    @GetMapping("/available")
    public List<MenuItem> getAvailableMenuItems() {
        return menuItemRepository.findByAvailabilityTrue();
    }

    // ✅ GET – Menu item by ID
    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable Integer id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Menu item not found with id: " + id));
    }

    // ✅ POST – Create menu item
    @PostMapping
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    // ✅ PUT – Full update (safe update)
    @PutMapping("/{id}")
    public MenuItem updateMenuItem(
            @PathVariable Integer id,
            @RequestBody MenuItem updatedItem) {

        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Menu item not found with id: " + id));

        existing.setItemName(updatedItem.getItemName());
        existing.setPrice(updatedItem.getPrice());
        existing.setDescription(updatedItem.getDescription());
        existing.setAvailability(updatedItem.getAvailability());
        existing.setIsVeg(updatedItem.getIsVeg());
        existing.setImageUrl(updatedItem.getImageUrl());
        existing.setPrepTimeMin(updatedItem.getPrepTimeMin());
        existing.setCategory(updatedItem.getCategory());
        existing.setAdmin(updatedItem.getAdmin());
        existing.setDrinksInventory(updatedItem.getDrinksInventory());

        return menuItemRepository.save(existing);
    }

    // ✅ PATCH – Toggle availability (ONLY id)
    @PatchMapping("/{id}")
    public MenuItem toggleAvailability(@PathVariable Integer id) {

        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Menu item not found with id: " + id));

        menuItem.setAvailability(!menuItem.getAvailability());
        return menuItemRepository.save(menuItem);
    }

    // ✅ DELETE – Remove menu item
    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Integer id) {
        menuItemRepository.deleteById(id);
    }
}
