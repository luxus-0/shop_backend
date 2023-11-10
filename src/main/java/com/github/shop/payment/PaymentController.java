package com.github.shop.payment;

import com.github.shop.payment.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payment>> readPayment() {
        return ResponseEntity.status(OK)
                .body(paymentService.getPayment());
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentDto payment) {
        return ResponseEntity.status(CREATED)
                .body(paymentService.createPayment(payment));
    }
}
