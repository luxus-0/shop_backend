package com.github.shop.cart;

import com.github.shop.cart.dto.CartSummaryDto;
import com.github.shop.cart.dto.SummaryDto;
import com.github.shop.cartitem.CartItem;
import com.github.shop.cartitem.dto.CartSummaryItemDto;
import com.github.shop.image.Image;
import com.github.shop.product.Product;
import com.github.shop.product.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public class CartMapper {

    public static CartSummaryDto mapToCartSummary(Cart cart) {
        return CartSummaryDto.builder()
                .id(cart.getId())
                .items(mapToCartItems(cart.getItems()))
                .summary(mapToSummary(cart.getItems()))
                .build();
    }

    private static List<CartSummaryItemDto> mapToCartItems(List<CartItem> items) {
        return items.stream()
                .map(CartMapper::mapToCartItem)
                .toList();
    }

    private static SummaryDto mapToSummary(List<CartItem> items) {
        return SummaryDto.builder()
                .grossValue(sumValues(items))
                .build();
    }

    private static BigDecimal sumValues(List<CartItem> items) {
        return items.stream()
                .map(CartMapper::calculateValue)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static CartSummaryItemDto mapToCartItem(CartItem cartItem) {
        return CartSummaryItemDto.builder()
                .id(cartItem.getId())
                .productDto(mapToProductDto(cartItem.getProduct()))
                .quantity(cartItem.getQuantity())
                .value(calculateValue(cartItem))
                .build();
    }

    private static BigDecimal calculateValue(CartItem cartItem) {
        return cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }

    private static ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .currency(product.getCurrency())
                .image(getImages(product.getImages()))
                .price(product.getPrice())
                .slug(product.getSlug())
                .build();
    }

    private static Image getImages(List<Image> images) {
        return images.stream().findAny().orElseThrow();
    }
}
