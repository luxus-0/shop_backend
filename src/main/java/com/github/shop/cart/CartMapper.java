package com.github.shop.cart;

import com.github.shop.cart.dto.CartSummaryDto;
import com.github.shop.cart.dto.SummaryDto;
import com.github.shop.cartitem.CartItem;
import com.github.shop.cartitem.dto.CartSummaryItemDto;
import com.github.shop.image.ImageNameNotFoundException;
import com.github.shop.image.ImagePathNotFoundException;
import com.github.shop.image.ImageTypeNotFoundException;
import com.github.shop.product.Product;
import com.github.shop.product.dto.ProductDto;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.github.shop.image.ImageMapper.mapToImageDto;
import static com.github.shop.slug.SlugifyMapper.slugify;

@Log4j2
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

    private static ProductDto mapToProductDto(Product product){
        try {
            return ProductDto.builder()
                    .name(product.getName())
                    .currency(product.getCurrency())
                    .image(mapToImageDto(product))
                    .price(product.getPrice())
                    .slug(slugify(product.getSlug()))
                    .build();
        } catch (ImageNameNotFoundException | ImageTypeNotFoundException | ImagePathNotFoundException e) {
            log.error(e.getMessage());
        }
        return Optional.of(ProductDto.builder().build()).orElseThrow();
    }
}
