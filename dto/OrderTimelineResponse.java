package com.canteen.canteen_backend.dto;

import java.sql.Timestamp;

public class OrderTimelineResponse {

    private Integer orderId;
    private String currentStatus;

    private Timestamp placedAt;
    private Timestamp confirmedAt;
    private Timestamp preparingAt;
    private Timestamp readyAt;
    private Timestamp deliveredAt;

    // ---------- Constructor ----------
    public OrderTimelineResponse(Integer orderId,
                                 String currentStatus,
                                 Timestamp placedAt,
                                 Timestamp confirmedAt,
                                 Timestamp preparingAt,
                                 Timestamp readyAt,
                                 Timestamp deliveredAt) {

        this.orderId = orderId;
        this.currentStatus = currentStatus;
        this.placedAt = placedAt;
        this.confirmedAt = confirmedAt;
        this.preparingAt = preparingAt;
        this.readyAt = readyAt;
        this.deliveredAt = deliveredAt;
    }

    // ---------- Getters ----------
    public Integer getOrderId() {
        return orderId;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public Timestamp getPlacedAt() {
        return placedAt;
    }

    public Timestamp getConfirmedAt() {
        return confirmedAt;
    }

    public Timestamp getPreparingAt() {
        return preparingAt;
    }

    public Timestamp getReadyAt() {
        return readyAt;
    }

    public Timestamp getDeliveredAt() {
        return deliveredAt;
    }
}
