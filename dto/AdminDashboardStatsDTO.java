package com.canteen.canteen_backend.dto;

public class AdminDashboardStatsDTO {

    private long totalAdmins;
    private long menuItems;
    private long totalOrders;
    private long pendingOrders;
    private double totalRevenue;

    public AdminDashboardStatsDTO(long totalAdmins,
                                  long menuItems,
                                  long totalOrders,
                                  long pendingOrders,
                                  double totalRevenue) {
        this.totalAdmins = totalAdmins;
        this.menuItems = menuItems;
        this.totalOrders = totalOrders;
        this.pendingOrders = pendingOrders;
        this.totalRevenue = totalRevenue;
    }

    public long getTotalAdmins() {
        return totalAdmins;
    }

    public long getMenuItems() {
        return menuItems;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public long getPendingOrders() {
        return pendingOrders;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}
