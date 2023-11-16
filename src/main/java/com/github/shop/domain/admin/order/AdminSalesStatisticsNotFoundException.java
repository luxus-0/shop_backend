package com.github.shop.domain.admin.order;

public class AdminSalesStatisticsNotFoundException extends Exception {
    public AdminSalesStatisticsNotFoundException() {
        super("Statistics sales not found");
    }
}
