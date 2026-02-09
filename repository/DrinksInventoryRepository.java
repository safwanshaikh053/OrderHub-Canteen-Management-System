package com.canteen.canteen_backend.repository;

import com.canteen.canteen_backend.entity.DrinksInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinksInventoryRepository
        extends JpaRepository<DrinksInventory, Integer> {
}
