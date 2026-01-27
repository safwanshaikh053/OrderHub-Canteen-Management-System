package com.canteen.canteen_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Drinks_Inventory")
@Data
public class DrinksInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer drinkId;

    @Column(nullable = false)
    private String drinkName;

    @Column(nullable = false)
    private String unit;   // bottle, can, cup

    @Column(nullable = false)
    private Integer currentStock;

    @Column(nullable = false)
    private Integer minStockLevel;

    private Date lastRestockDate;

    private Boolean isActive = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updatedAt;

    // ---------- Getters & Setters ----------

    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    public Integer getMinStockLevel() {
        return minStockLevel;
    }

    public void setMinStockLevel(Integer minStockLevel) {
        this.minStockLevel = minStockLevel;
    }

    public Date getLastRestockDate() {
        return lastRestockDate;
    }

    public void setLastRestockDate(Date lastRestockDate) {
        this.lastRestockDate = lastRestockDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    // ---------- Constructors ----------

    public DrinksInventory() {
        super();
    }

    public DrinksInventory(Integer drinkId, String drinkName, String unit,
                           Integer currentStock, Integer minStockLevel,
                           Date lastRestockDate, Boolean isActive,
                           Timestamp createdAt, Timestamp updatedAt) {
        super();
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.unit = unit;
        this.currentStock = currentStock;
        this.minStockLevel = minStockLevel;
        this.lastRestockDate = lastRestockDate;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
