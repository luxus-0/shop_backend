package com.github.shop.admin.order;

public class AdminSalesStatisticsNotFoundException extends Exception{
    public AdminSalesStatisticsNotFoundException(){
        super("Statistics sales not found");
    }
}
