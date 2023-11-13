package com.github.shop.domain.shipment;

import lombok.Getter;

@Getter
public enum ShipmentStatus {
    SENT,
    DELIVERED,
    IN_TRANSIT,
    AWAITING_PICKUP,
    RETURNED,
    LOST,
    CANCELLED,
    ON_HOLD,
    AWAITING_SORTING,
    OTHER
}
