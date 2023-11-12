package com.github.shop.admin.order;

import com.github.shop.admin.order.dto.AdminOrderDto;
import com.github.shop.admin.order.dto.AdminOrderStatusesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.github.shop.admin.order.AdminOrderService.createAdminOrderStatusesMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/orders")
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    @GetMapping
    ResponseEntity<Page<AdminOrderDto>> getOrders(Pageable pageable) {
        return ResponseEntity.ok(AdminOrderMapper.mapToPageDtos(adminOrderService.getOrders(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminOrder> getOrders(@PathVariable Long id) {
        return ResponseEntity.ok(adminOrderService.getOrder(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> actualizeOrder(@PathVariable Long id, @RequestBody Map<String, String> values) throws AdminOrderStatusNotFound {
        adminOrderService.patchOrder(id, values);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statuses")
    public ResponseEntity<AdminOrderStatusesDto> getAdminOrderStatuses() {
        return ResponseEntity.ok(new AdminOrderStatusesDto(createAdminOrderStatusesMap()));
    }
}
