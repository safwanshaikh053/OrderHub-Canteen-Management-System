package com.canteen.canteen_backend.service;

import org.springframework.stereotype.Service;
import com.canteen.canteen_backend.entity.OrderItem;

@Service
public class OrderItemService {

    private static final double TAX_RATE = 0.05; // 5% GST

    /**
     * ✅ Main method used while placing an order
     * Calculates subtotal, tax, discount and final amount
     */
    public OrderItem calculateAmounts(OrderItem item) {

        validateItem(item);

        double baseAmount = calculateBaseAmount(item);
        double taxAmount = calculateTax(baseAmount);
        double discountAmount = calculateDiscount(item);
        double finalAmount = calculateFinalAmount(baseAmount, taxAmount, discountAmount);

        item.setSubtotal(baseAmount);
        item.setTaxAmount(taxAmount);
        item.setDiscountAmount(discountAmount);
       

        return item;
    }

    /**
     * quantity × unit price
     */
    public double calculateBaseAmount(OrderItem item) {
        return item.getQuantity() * item.getUnitPrice();
    }

    /**
     * 5% tax calculation
     */
    public double calculateTax(double baseAmount) {
        return baseAmount * TAX_RATE;
    }

    /**
     * Discount handling (null safe)
     */
    public double calculateDiscount(OrderItem item) {
        return item.getDiscountAmount() != null
                ? item.getDiscountAmount()
                : 0.0;
    }

    /**
     * Final amount = base + tax − discount
     */
    public double calculateFinalAmount(double base, double tax, double discount) {
        return base + tax - discount;
    }

    /**
     * Basic validation to prevent bad data
     */
    private void validateItem(OrderItem item) {

        if (item.getQuantity() == null || item.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than zero");
        }

        if (item.getUnitPrice() == null || item.getUnitPrice() <= 0) {
            throw new RuntimeException("Unit price must be greater than zero");
        }
    }
}
