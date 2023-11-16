package com.github.shop.domain.order.dto;

import com.github.shop.domain.payment.Payment;
import com.github.shop.domain.shipment.Shipment;
import lombok.Builder;

import java.util.List;

@Builder
public record InitOrder(List<Shipment> shipments, List<Payment> payments) {
}
