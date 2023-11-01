package com.github.Shop.cartitem;

import com.github.Shop.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @OneToOne
    private Product product;
    private Long cartId;
}
