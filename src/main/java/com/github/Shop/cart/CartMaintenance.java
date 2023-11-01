package com.github.Shop.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.github.Shop.constant.Constants.CRON_CART;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartMaintenance {

    private final CartRepository cartRepository;

    @Scheduled(cron = "${basket.cleanup.expression}")
    public void removeInactiveBasket(){
        List<Cart> carts = cartRepository.findByCreatedLessThan(LocalDateTime.now().minusDays(3));
        log.info(carts);
    }
}
