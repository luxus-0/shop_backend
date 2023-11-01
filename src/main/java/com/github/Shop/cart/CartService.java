package com.github.Shop.cart;

import com.github.Shop.cart.dto.CartProductDto;
import com.github.Shop.cartitem.CartItem;
import com.github.Shop.product.Product;
import com.github.Shop.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    Cart findCart(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Cart addProductToCart(Long id, CartProductDto cartProductDto) {
        Cart cart = getInitializedCart(id);
        if(cart != null) {
            cart.addProduct(CartItem.builder()
                    .product(getProduct(cartProductDto.productId()))
                    .quantity(cartProductDto.quantity())
                    .cartId(cart.getId())
                    .build());
            return cart;
        }
        return Cart.builder().build();
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    private Cart getInitializedCart(Long id) {
        if (id == null || id <= 0) {
            return cartRepository.save(Cart.builder()
                    .created(now())
                    .build());
        }
        return cartRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Cart updateCart(Long id, List<CartProductDto> cartProductDtos) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cart.getItems().forEach(cartItem -> {
            cartProductDtos.stream()
                    .filter(cartProductDto -> cartItem.getProduct().getId().equals(cartProductDto.productId()))
                    .findFirst()
                    .ifPresent(cartProductDto -> cartItem.setQuantity(cartProductDto.quantity()));
        });
        return cart;
    }
}
