package com.github.Shop.adminorder;

import com.github.Shop.adminorder.dto.AdminOrderStats;
import com.github.Shop.adminorder.dto.AdminOrderStatsValue;
import com.github.Shop.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class AdminOrderStatsService {

    private final AdminOrderRepository adminOrderRepository;

    List<AdminOrder> getAdminOrdersForAllMonth() {
        LocalDateTime from = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime to = LocalDateTime.now();
        return adminOrderRepository.findAllByPlaceDateBetweenAndOrderStatus(
                from,
                to,
                OrderStatus.COMPLETED);

    }

    AdminOrderStats getStatistics() {
        List<AdminOrder> adminOrders = getAdminOrdersForAllMonth();
        long daysOfMonth = getDaysOfMonth(adminOrders);
        TreeMap<Long, AdminOrderStatsValue> results = new TreeMap<>();
        results.put(daysOfMonth, aggregateValues(adminOrders));

        List<Long> labelDays = results.keySet().stream().toList();
        return AdminOrderStats.builder()
                .days(labelDays)
                .sales(getSales(results))
                .orders(getOrders(results))
                .build();
    }

    private static List<BigDecimal> getSales(TreeMap<Long, AdminOrderStatsValue> results) {
        return results.values().stream()
                .map(order -> order.statistics().sales()
                        .stream()
                        .findAny()
                        .orElseThrow())
                .toList();
    }

    private static List<Long> getOrders(TreeMap<Long, AdminOrderStatsValue> results) {
        return results.values().stream()
                .map(order -> order.statistics().orders()
                        .stream()
                        .findAny()
                        .orElseThrow())
                .toList();
    }

    private static long getDaysOfMonth(List<AdminOrder> adminOrders) {
        return adminOrders.stream()
                .map(order -> order.getPlaceDate().getDayOfMonth())
                .findAny()
                .orElseThrow();
    }

    private AdminOrderStatsValue aggregateValues(List<AdminOrder> adminOrders) {
        long daysOfMonth = getDaysOfMonth(adminOrders);
        BigDecimal totalValue = getTotalValue(adminOrders, daysOfMonth);
        long orderCount = getOrderCount(adminOrders, daysOfMonth);
        return AdminOrderStatsValue.builder()
                .orderCount(orderCount)
                .totalValue(totalValue)
                .build();
    }

    private static long getOrderCount(List<AdminOrder> adminOrders, long daysOfMonth) {
        return adminOrders.stream()
                .filter(order -> daysOfMonth == order.getPlaceDate().getDayOfMonth())
                .count();
    }

    private static BigDecimal getTotalValue(List<AdminOrder> adminOrders, long daysOfMonth) {
        return adminOrders.stream()
                .filter(order -> daysOfMonth == order.getPlaceDate().getDayOfMonth())
                .map(AdminOrder::getGrossValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
