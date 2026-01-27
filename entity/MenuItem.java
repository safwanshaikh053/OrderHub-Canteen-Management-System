package com.canteen.canteen_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Menu_Item")
@Data
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuItemId;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Double price;

    private String description;

    private Boolean availability = true;

    private Boolean isVeg = true;

    private String imageUrl;

    private Integer prepTimeMin;

    // 🔗 Many menu items → one category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "drink_id")
    private DrinksInventory drinksInventory;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updatedAt;

    // ---------- Getters & Setters ----------

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailability() {
        return availability;
    }
    
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Boolean getIsVeg() {
        return isVeg;
    }
    
    public void setIsVeg(Boolean isVeg) {
        this.isVeg = isVeg;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrepTimeMin() {
        return prepTimeMin;
    }
    
    public void setPrepTimeMin(Integer prepTimeMin) {
        this.prepTimeMin = prepTimeMin;
    }

    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }

    public Admin getAdmin() {
        return admin;
    }
    
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public DrinksInventory getDrinksInventory() {
        return drinksInventory;
    }
    
    public void setDrinksInventory(DrinksInventory drinksInventory) {
        this.drinksInventory = drinksInventory;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    // ---------- Constructors ----------

    public MenuItem() {
        super();
    }

    public MenuItem(Integer menuItemId, String itemName, Double price, String description,
                    Boolean availability, Boolean isVeg, String imageUrl, Integer prepTimeMin,
                    Category category, Admin admin, DrinksInventory drinksInventory,
                    Timestamp createdAt, Timestamp updatedAt) {
        super();
        this.menuItemId = menuItemId;
        this.itemName = itemName;
        this.price = price;
        this.description = description;
        this.availability = availability;
        this.isVeg = isVeg;
        this.imageUrl = imageUrl;
        this.prepTimeMin = prepTimeMin;
        this.category = category;
        this.admin = admin;
        this.drinksInventory = drinksInventory;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
