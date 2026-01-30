package com.canteen.canteen_backend.controller;

import com.canteen.canteen_backend.entity.DrinksInventory;
import com.canteen.canteen_backend.repository.DrinksInventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/drinks")
@CrossOrigin
public class DrinksInventoryController {

    private final DrinksInventoryRepository drinksInventoryRepository;

    public DrinksInventoryController(DrinksInventoryRepository drinksInventoryRepository) {
        this.drinksInventoryRepository = drinksInventoryRepository;
    }

    // ✅ GET – All drinks
    @GetMapping
    public List<DrinksInventory> getAllDrinks() {
        return drinksInventoryRepository.findAll();
    }

    // ✅ POST – Add drink
    @PostMapping
    public DrinksInventory addDrink(@RequestBody DrinksInventory drink) {
        return drinksInventoryRepository.save(drink);
    }

    // ✅ PUT – Update stock/details
    @PutMapping("/{id}")
    public DrinksInventory updateDrink(
            @PathVariable Integer id,
            @RequestBody DrinksInventory updatedDrink) {

        DrinksInventory existing = drinksInventoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Drink not found with id: " + id));

        existing.setDrinkName(updatedDrink.getDrinkName());
        existing.setUnit(updatedDrink.getUnit());
        existing.setCurrentStock(updatedDrink.getCurrentStock());
        existing.setMinStockLevel(updatedDrink.getMinStockLevel());
        existing.setIsActive(updatedDrink.getIsActive());

        return drinksInventoryRepository.save(existing);
    }

    // ✅ PATCH – Toggle availability
    @PatchMapping("/{id}")
    public DrinksInventory toggleDrinkStatus(@PathVariable Integer id) {

        DrinksInventory drink = drinksInventoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Drink not found with id: " + id));

        drink.setIsActive(!drink.getIsActive());
        return drinksInventoryRepository.save(drink);
    }
}
