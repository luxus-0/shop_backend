package com.github.shop.domain.cart;

import com.github.shop.domain.cart.dto.CartProductDto;
import com.github.shop.domain.cartitem.CartItem;
import com.github.shop.domain.product.Product;
import com.github.shop.domain.product.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@AllArgsConstructor
@Log4j2
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    Cart findCart(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Cart addProductToCart(Long id, CartProductDto cartProductDto) {
        Cart cart = getInitializedCart(id);
        cart.addProduct(CartItem.builder()
                .product(getProduct(cartProductDto.productId()))
                .quantity(cartProductDto.quantity())
                .cartId(cart.getId())
                .build());
        return cart;
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    private Cart getInitializedCart(Long id) {
        if (id == null || id <= 0) {
            return saveNewCart();
        }
        return cartRepository.findById(id).orElseGet(this::saveNewCart);
    }

    private Cart saveNewCart() {
        return cartRepository.save(Cart.builder().created(now()).build());
    }

    @Transactional
    public Cart updateCart(Long id, List<CartProductDto> cartProductDtos) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cart.getItems().forEach(cartItem -> cartProductDtos.stream()
                .filter(cartProductDto -> cartItem.getProduct().getId().equals(cartProductDto.productId()))
                .findFirst()
                .ifPresent(cartProductDto -> cartItem.setQuantity(cartProductDto.quantity())));
        return cart;
    }
}
