package com.canteen.canteen_backend.dto;

import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;

    public ErrorResponse(LocalDateTime timestamp, int status, String error) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
