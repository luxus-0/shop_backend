package com.github.shop.admin.order;

public class AdminOrdersStatisticsNotFoundException extends Exception{
    public AdminOrdersStatisticsNotFoundException(){
        super("Statistics orders not found");
    }
}
