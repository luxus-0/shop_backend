package com.github.Shop.shipment.dto;

import com.github.Shop.shipment.Recipient;
import com.github.Shop.shipment.Sender;
import com.github.Shop.shipment.ShipmentStatus;
import com.github.Shop.shipment.ShippingType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
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
