package com.github.shop.shipment;

public class ShipmentNotFoundException extends Exception {
    public ShipmentNotFoundException() {
        super("Shipment not found");
    }
}
