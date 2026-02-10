package com.canteen.canteen_backend.dto;

public class LoginResponse {

    private Integer customerId;
    private String name;
    private String email;

    public LoginResponse(Integer customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
