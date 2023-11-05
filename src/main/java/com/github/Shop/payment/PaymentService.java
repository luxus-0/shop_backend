package com.github.Shop.payment;

import com.github.Shop.order.dto.OrderDto;
import com.github.Shop.payment.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    List<Payment> getPayment() {
        return paymentRepository.findAll();
    }

    Payment createPayment(PaymentDto payment) {
        Payment createPayment = Payment.builder()
                .name(payment.name())
                .type(payment.type())
                .description(payment.description())
                .isPayment(payment.isPayment())
                .build();
        return paymentRepository.save(createPayment);
    }
}
