package com.github.Shop.cartitem;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cartItems")
public class CartItemController {

    private final CartItemService cartItemService;

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id){
        cartItemService.delete(id);
    }

}
