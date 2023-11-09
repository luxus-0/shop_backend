package com.github.shop.adminorder;

public class StatisticsOrdersNotFoundException extends Exception{
    public StatisticsOrdersNotFoundException(){
        super("Statistics orders not found");
    }
}
