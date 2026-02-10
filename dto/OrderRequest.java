package com.canteen.canteen_backend.dto;



import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class OrderRequest {

    @NotBlank(message = "Order type is required")
    private String orderType;   // DINE_IN / TAKEAWAY

    private String specialInstructions;

    
    private List<OrderItemRequest> items;
    // ✅ No-args constructor
    public OrderRequest() {
    }

    // ✅ All-args constructor
    public OrderRequest(String orderType, String specialInstructions) {
        this.orderType = orderType;
        this.specialInstructions = specialInstructions;
    }

    // ✅ Getters & Setters

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

	public List<OrderItemRequest> getItems() {
		return items;
	}

	public void setItems(List<OrderItemRequest> items) {
		this.items = items;
	}
}
