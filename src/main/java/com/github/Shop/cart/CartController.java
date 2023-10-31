package com.github.Shop.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;
    @GetMapping("/{id}")
    public Cart gerCart(@PathVariable Long id){
        return cartService.findCart(id);
    }

    @PutMapping("/{id}")
    public Cart createProductToCart(@PathVariable Long id, @RequestBody CartProductDto cartProductDto){
        return cartService.addProductToCart(id, cartProductDto);
    }
}
