package com.github.Shop.cart;

import com.github.Shop.cartitem.CartItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartMaintenance {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Scheduled(cron = "${basket.cleanup.expression}")
    public void removeInactiveBasket(){
        List<Cart> carts = cartRepository.findByCreatedLessThan(LocalDateTime.now().minusDays(3));
        log.info("Old baskets: " +carts.size());
        carts.forEach(cart -> {
            cartItemRepository.deleteByCartId(cart.getId());
            cartRepository.deleteCartById(cart.getId());
        });
    }
}
