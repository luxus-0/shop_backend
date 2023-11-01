package com.github.Shop.cart;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDateTime;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCreatedLessThan(LocalDateTime LocalDateTime);
    @Query("delete from Cart c where c.id = :id")
    @Modifying
    void deleteCartById(Long id);
}
