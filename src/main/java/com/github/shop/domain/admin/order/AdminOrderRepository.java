package com.github.shop.domain.admin.order;

import com.github.shop.domain.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminOrderRepository extends JpaRepository<AdminOrder, Long> {
    List<AdminOrder> findAllByPlaceDateBetweenAndOrderStatus(LocalDateTime start, LocalDateTime end, OrderStatus orderStatus);
}
