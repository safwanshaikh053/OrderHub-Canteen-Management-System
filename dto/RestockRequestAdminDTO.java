package com.canteen.canteen_backend.dto;

import java.sql.Timestamp;

public class RestockRequestAdminDTO {

    private Integer requestId;
    private Integer staffId;
    private String itemName;
    private Integer requestedQuantity;
    private String status;
    private String note;
    private Timestamp requestedAt;

    // ✅ REQUIRED: No-arg constructor
    public RestockRequestAdminDTO() {
    }

    // ✅ Constructor used by controller
    public RestockRequestAdminDTO(
            Integer requestId,
            Integer staffId,
            String itemName,
            Integer requestedQuantity,
            String status,
            String note,
            Timestamp requestedAt
    ) {
        this.requestId = requestId;
        this.staffId = staffId;
        this.itemName = itemName;
        this.requestedQuantity = requestedQuantity;
        this.status = status;
        this.note = note;
        this.requestedAt = requestedAt;
    }

    // ✅ GETTERS (THIS IS WHY {} WAS RETURNED)

    public Integer getRequestId() {
        return requestId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getRequestedQuantity() {
        return requestedQuantity;
    }

    public String getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public Timestamp getRequestedAt() {
        return requestedAt;
    }
}
