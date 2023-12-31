package com.github.shop.domain.payment;

import com.github.shop.domain.payment.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    public Payment createPayment(PaymentDto payment) {
        Payment createPayment = Payment.builder()
                .name(payment.name())
                .type(payment.type())
                .description(payment.description())
                .isPayment(payment.isPayment())
                .build();
        return paymentRepository.save(createPayment);
    }
}
