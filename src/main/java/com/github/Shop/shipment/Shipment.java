package com.github.Shop.shipment;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private UUID trackingNumber;
    @NotBlank
    @Enumerated(EnumType.STRING)
    @NotBlank
    private ShipmentStatus status;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private ShippingType shippingType;
    @NotBlank
    @Future
    private LocalDateTime deliveryDate;
    @NotBlank
    private BigDecimal price;
    @OneToOne
    private Sender sender;
    @OneToOne
    private Recipient recipient;
}
