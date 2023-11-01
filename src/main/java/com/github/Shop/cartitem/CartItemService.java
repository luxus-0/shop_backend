package com.github.Shop.cartitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public void delete(Long id){
        cartItemRepository.deleteById(id);
    }
}
