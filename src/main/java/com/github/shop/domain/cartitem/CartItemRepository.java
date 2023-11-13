package com.github.shop.domain.cartitem;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Long countByCartId(Long cartId);

    @Query("delete from CartItem ci where ci.cart_id in (:cardId)")
    @Modifying
    void deleteAllByCartIdIn(List<Long> ids);

    void deleteAllByCartId(Long id);
}
