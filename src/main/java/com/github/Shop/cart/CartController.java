package com.github.Shop.cart;

import com.github.Shop.cart.dto.CartProductDto;
import com.github.Shop.cart.dto.CartSummaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    public CartSummaryDto gerCart(@PathVariable Long id) {
        return CartMapper.mapToCartSummary(cartService.findCart(id));
    }

    @PutMapping("/{id}")
    public CartSummaryDto createProductToCart(@PathVariable Long id, @RequestBody CartProductDto cartProductDto) {
        return CartMapper.mapToCartSummary(cartService.addProductToCart(id, cartProductDto));
    }
}
