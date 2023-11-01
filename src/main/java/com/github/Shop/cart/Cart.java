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
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cartId")
    private List<CartItem> items;

    void addProduct(CartItem cartItems) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(cartItems);
    }
}
