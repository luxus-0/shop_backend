package com.github.shop.order.dto;

import com.github.shop.address.dto.AddressDto;
import com.github.shop.contact.dto.ContactDto;
import com.github.shop.customer.dto.CustomerDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record OrderDto(@NotNull Long cartId,
                       @NotNull Long shipmentId,
                       @NotNull Long paymentId,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")
                       LocalDateTime placeDate,
                       @NotBlank CustomerDto customer,
                       @NotBlank ContactDto contact,
                       @NotBlank AddressDto address) {
}
