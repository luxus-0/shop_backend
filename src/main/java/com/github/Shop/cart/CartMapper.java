package com.github.Shop.cart;

import com.github.Shop.cart.dto.CartSummaryDto;
import com.github.Shop.cart.dto.SummaryDto;
import com.github.Shop.cartitem.CartItem;
import com.github.Shop.cartitem.dto.CartSummaryItemDto;
import com.github.Shop.image.Image;
import com.github.Shop.product.Product;
import com.github.Shop.product.dto.ProductDto;

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
                .orElseThrow();
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
