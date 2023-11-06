package com.github.Shop.adminorder;

import com.github.Shop.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final AdminOrderRepository adminOrderRepository;

    public Page<AdminOrder> getOrders(Pageable pageable) {
        return adminOrderRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        Sort.by("id")
                                .descending()));
    }

    public AdminOrder getOrder(Long id) {
        return adminOrderRepository.findById(id).orElseThrow();
    }

    public void patchOrder(Long id, Map<String, String> values) {
        AdminOrder adminOrder = adminOrderRepository.findById(id).orElseThrow();
        patchValues(adminOrder, values);
    }

    private void patchValues(AdminOrder adminOrder, Map<String, String> values) {
        if (values.get("orderStatus") != null) {
            AdminOrder.builder()
                    .id(adminOrder.getId())
                    .orderStatus(OrderStatus.NEW)
                    .build();
        }
    }
    public static Map<String, String> createAdminOrderStatusesMap() {
        Map<String, String> statuses = new HashMap<>();
        for(OrderStatus value  : OrderStatus.values()){
            statuses.put(value.name(), value.getValue());
        }
        return statuses;
    }
}
