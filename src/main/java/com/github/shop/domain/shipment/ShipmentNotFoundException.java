package com.github.shop.domain.shipment;

public class ShipmentNotFoundException extends Exception {
    public ShipmentNotFoundException() {
        super("Shipment not found");
    }
}
