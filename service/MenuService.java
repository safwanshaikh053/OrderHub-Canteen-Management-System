package com.canteen.canteen_backend.service;

import com.canteen.canteen_backend.entity.MenuItem;
import com.canteen.canteen_backend.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuItemRepository menuItemRepository;

    //  MANUAL CONSTRUCTOR
    public MenuService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getAvailableMenu() {
        return menuItemRepository.findByAvailabilityTrue();
    }
}
