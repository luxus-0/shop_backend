package com.github.Shop.order.dto;

import com.github.Shop.order.OrderStatus;
import com.github.Shop.payment.Payment;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record OrderSummary(@NotBlank Long id, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime placeDate,
                           @NotBlank OrderStatus status, @Min(0) BigDecimal grossValue, Payment payment) {
}
