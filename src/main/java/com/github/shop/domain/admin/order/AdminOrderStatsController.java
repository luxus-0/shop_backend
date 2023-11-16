package com.github.shop.domain.admin.order;

import com.github.shop.domain.admin.order.dto.AdminOrderStats;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/order/stats")
public class AdminOrderStatsController {

    private final AdminOrderStatsService orderStatsService;

    @GetMapping
    public ResponseEntity<AdminOrderStats> readStatisticsOrder() throws AdminSalesStatisticsNotFoundException, AdminOrdersStatisticsNotFoundException {
        return ResponseEntity.ok().body(orderStatsService.getStatisticsOrder());
    }
}
