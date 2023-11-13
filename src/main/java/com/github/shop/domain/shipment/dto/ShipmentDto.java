package com.github.shop.domain.shipment.dto;

import com.github.shop.domain.shipment.ShipmentStatus;
import com.github.shop.domain.shipment.ShippingType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record ShipmentDto(@NotBlank UUID trackingNumber,
                          @NotBlank ShipmentStatus status,
                          @NotBlank ShippingType shippingType,
                          @NotBlank @Future LocalDateTime deliveryDate,
                          @NotBlank SenderDto sender,
                          @NotBlank RecipientDto recipient) {
}
