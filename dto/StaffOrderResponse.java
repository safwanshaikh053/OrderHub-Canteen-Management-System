package com.canteen.canteen_backend.dto;

import java.util.List;

public class StaffOrderResponse {

    private Integer orderId;
    private Integer customerId;
    private String customerName;
    private Double totalAmount;
    private List<String> items;

    public StaffOrderResponse(
            Integer orderId,
            Integer customerId,
            String customerName,
            Double totalAmount,
            List<String> items) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public Integer getOrderId() { return orderId; }
    public Integer getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public Double getTotalAmount() { return totalAmount; }
    public List<String> getItems() { return items; }
}
