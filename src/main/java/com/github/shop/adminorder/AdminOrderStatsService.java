package com.github.shop.adminorder;

import com.github.shop.adminorder.dto.AdminOrderStats;
import com.github.shop.adminorder.dto.AdminOrderStatsValue;
import com.github.shop.order.OrderStatus;
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

    public AdminOrderStats getStatisticsOrder() throws StatisticsSalesNotFoundException, StatisticsOrdersNotFoundException {
        List<AdminOrder> adminOrders = fetchOrdersForAllMonth();
        long daysOfMonth = calculateDaysOfMonth(adminOrders);
        TreeMap<Long, AdminOrderStatsValue> results = new TreeMap<>();
        results.put(daysOfMonth, aggregateValues(adminOrders));
        List<Long> labelDays = extractDays(results);

        return AdminOrderStats.builder()
                .days(labelDays)
                .sales(extractSales(results))
                .orders(extractOrders(results))
                .build();
    }

    private List<Long> extractDays(TreeMap<Long, AdminOrderStatsValue> results) {
        return results.keySet().stream().toList();
    }

    private List<BigDecimal> extractSales(TreeMap<Long, AdminOrderStatsValue> results) throws StatisticsSalesNotFoundException {
        return results.values().stream()
                .map(order -> order.statistics().sales())
                .findAny()
                .orElseThrow(StatisticsSalesNotFoundException::new)
                .stream()
                .toList();
    }

    private List<Long> extractOrders(TreeMap<Long, AdminOrderStatsValue> results) throws StatisticsOrdersNotFoundException {
        return results.values().stream()
                .map(order -> order.statistics().orders())
                .findAny()
                .orElseThrow(StatisticsOrdersNotFoundException::new)
                .stream()
                .toList();
    }

    private long calculateDaysOfMonth(List<AdminOrder> adminOrders) {
        return adminOrders.stream()
                .map(order -> order.getPlaceDate().getDayOfMonth())
                .findAny()
                .orElseThrow();
    }

    private long calculateOrderCount(List<AdminOrder> adminOrders, long daysOfMonth) {
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

    private List<AdminOrder> fetchOrdersForAllMonth() {
        LocalDateTime from = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime to = LocalDateTime.now();
        return adminOrderRepository.findAllByPlaceDateBetweenAndOrderStatus(
                from,
                to,
                OrderStatus.COMPLETED);

    }

    private AdminOrderStatsValue aggregateValues(List<AdminOrder> adminOrders) {
        long daysOfMonth = calculateDaysOfMonth(adminOrders);
        BigDecimal totalValue = calculateTotalValue(adminOrders, daysOfMonth);
        long orderCount = calculateOrderCount(adminOrders, daysOfMonth);
        return AdminOrderStatsValue.builder()
                .orderCount(orderCount)
                .totalValue(totalValue)
                .build();
    }
}
