package com.github.shop.admin.order;

import com.github.shop.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/order/export")
public class AdminOrderExportController {

    private final AdminOrderExportService adminExportService;

    @GetMapping
    public ResponseEntity<Resource> exportOrdersToCSV(@RequestParam @DateTimeFormat LocalDateTime from,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,

                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OrderStatus orderStatus) {
        List<AdminOrder> adminOrders = adminExportService.exportOrders(from, to, orderStatus);
        ByteArrayInputStream inputStream = adminExportService.exportOrdersToCSV(adminOrders);
        Resource resource = new InputStreamResource(inputStream);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "orderExport.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }
}
