package com.canteen.canteen_backend.dto;

import java.sql.Timestamp;

public class OrderResponse {

    private Integer orderId;
    private String status;
    private Double totalAmount;
    private Double taxAmount;
    private Double discountAmount;
    private Timestamp orderDate;

    // ✅ No-args constructor
    public OrderResponse() {
    }

    // ✅ All-args constructor
    public OrderResponse(Integer orderId, String status,
                         Double totalAmount, Double taxAmount,
                         Double discountAmount, Timestamp orderDate) {
        this.orderId = orderId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.taxAmount = taxAmount;
        this.discountAmount = discountAmount;
        this.orderDate = orderDate;
    }

    // ✅ Getters & Setters

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }
    
    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }
    
    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}
