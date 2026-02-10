package com.canteen.canteen_backend.dto;

public class MenuItemCreateRequest {

    private String itemName;
    private Double price;
    private Integer categoryId;
    private Boolean isVeg = true;
    private Boolean availability = true;

    // TEMP (until JWT)
    private Integer adminId;

    // ---------- Getters & Setters ----------

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

    public Integer getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getIsVeg() {
        return isVeg;
    }
    
    public void setIsVeg(Boolean isVeg) {
        this.isVeg = isVeg;
    }

    public Boolean getAvailability() {
        return availability;
    }
    
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Integer getAdminId() {
        return adminId;
    }
    
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}
