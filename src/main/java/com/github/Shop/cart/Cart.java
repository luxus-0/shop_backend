package com.github.Shop.cart;

import com.github.Shop.cartitem.CartItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @FutureOrPresent
    private LocalDateTime created;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "cartId")
    private List<CartItem> items;

    void addProduct(CartItem cartItem) {
        if (items == null) {
            items = new ArrayList<>();
        }
        new ArrayList<>(items).stream()
                .filter(item -> Objects.equals(cartItem.getProduct().getId(), item.getProduct().getId()))
                .findFirst()
                .ifPresentOrElse(item -> item.setQuantity(item.getQuantity() + 1),
                        () -> items.add(cartItem));
    }
}
