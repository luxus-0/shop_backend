package com.github.shop.domain.admin.order;

public class AdminOrdersStatisticsNotFoundException extends Exception{
    public AdminOrdersStatisticsNotFoundException(){
        super("Statistics orders not found");
    }
}
