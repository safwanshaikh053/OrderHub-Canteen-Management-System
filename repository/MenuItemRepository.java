package com.canteen.canteen_backend.repository;

import com.canteen.canteen_backend.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    List<MenuItem> findByAvailabilityTrue();
}
