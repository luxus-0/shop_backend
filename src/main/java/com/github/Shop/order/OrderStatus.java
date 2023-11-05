package com.github.Shop.order;

import lombok.Getter;

@Getter
public enum OrderStatus {
    NEW,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    RETURNED,
    COMPLETED
}
