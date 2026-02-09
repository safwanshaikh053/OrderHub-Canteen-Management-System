package com.canteen.canteen_backend.repository;

import com.canteen.canteen_backend.entity.IngredientInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientInventoryRepository
        extends JpaRepository<IngredientInventory, Integer> {
}
