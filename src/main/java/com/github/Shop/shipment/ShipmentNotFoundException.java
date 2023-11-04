package com.github.Shop.shipment;

public class ShipmentNotFoundException extends Exception{
    public ShipmentNotFoundException(){
        super("Shipment not found");
    }
}
