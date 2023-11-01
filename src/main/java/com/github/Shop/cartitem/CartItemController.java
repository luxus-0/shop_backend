package com.github.Shop.cartitem;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cartItems")
public class CartItemController {

    private final CartItemManager cartItemManager;

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        cartItemManager.delete(id);
    }

    @GetMapping("/count/{cartId}")
    public Long countItemInCart(@PathVariable Long cartId) {
        return cartItemManager.countItemInCart(cartId);
    }

}
