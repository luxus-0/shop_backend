package com.github.shop.domain.admin.order;

import com.github.shop.domain.admin.order.dto.AdminOrderStats;
import com.github.shop.domain.admin.order.dto.AdminOrderStatsValue;
import com.github.shop.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class AdminOrderStatsService {

    private final AdminOrderRepository adminOrderRepository;

    public AdminOrderStats getStatisticsOrder() throws AdminSalesStatisticsNotFoundException, AdminOrdersStatisticsNotFoundException {
        List<AdminOrder> adminOrders = getOrdersForAllMonth();
        long daysOfMonth = getDaysOfMonth(adminOrders);
        TreeMap<Long, AdminOrderStatsValue> results = new TreeMap<>();
        results.put(daysOfMonth, aggregateValues(adminOrders));
        List<Long> labelDays = getDays(results);

        return AdminOrderStats.builder()
                .days(labelDays)
                .sales(getSales(results))
                .orders(getOrders(results))
                .build();
    }

    private List<Long> getDays(TreeMap<Long, AdminOrderStatsValue> results) {
        return results.keySet().stream().toList();
    }

    private List<BigDecimal> getSales(TreeMap<Long, AdminOrderStatsValue> results) throws AdminSalesStatisticsNotFoundException {
        return results.values().stream()
                .map(order -> order.statistics().sales())
                .flatMap(Collection::stream)
                .toList();
    }

    private List<Long> getOrders(TreeMap<Long, AdminOrderStatsValue> results) throws AdminOrdersStatisticsNotFoundException {
        return results.values().stream()
                .map(order -> order.statistics().orders())
                .flatMap(Collection::stream)
                .toList();
    }

    private long getDaysOfMonth(List<AdminOrder> adminOrders) {
        return adminOrders.stream()
                .map(order -> order.getPlaceDate().getDayOfMonth())
                .findAny()
                .orElseThrow();
    }

    private long countingOrders(List<AdminOrder> adminOrders, long daysOfMonth) {
        return adminOrders.stream()
                .filter(order -> daysOfMonth == order.getPlaceDate().getDayOfMonth())
                .count();
    }

    private BigDecimal calculateTotalValue(List<AdminOrder> adminOrders, long daysOfMonth) {
        return adminOrders.stream()
                .filter(order -> daysOfMonth == order.getPlaceDate().getDayOfMonth())
                .map(AdminOrder::getGrossValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<AdminOrder> getOrdersForAllMonth() {
        LocalDateTime from = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime to = LocalDateTime.now();
        return adminOrderRepository.findAllByPlaceDateBetweenAndOrderStatus(
                from,
                to,
                OrderStatus.COMPLETED);

    }

    private AdminOrderStatsValue aggregateValues(List<AdminOrder> adminOrders) {
        long daysOfMonth = getDaysOfMonth(adminOrders);
        BigDecimal totalValue = calculateTotalValue(adminOrders, daysOfMonth);
        long orderCount = countingOrders(adminOrders, daysOfMonth);
        return AdminOrderStatsValue.builder()
                .orderCount(orderCount)
                .totalValue(totalValue)
                .build();
    }
}
