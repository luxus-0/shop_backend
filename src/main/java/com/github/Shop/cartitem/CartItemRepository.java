package com.github.Shop.cartitem;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Long countByCartId(Long cartId);
    @Query("delete from CartItem ci where ci.cart_id = :cardId")
    void deleteByCartId(Long cartId);
}
