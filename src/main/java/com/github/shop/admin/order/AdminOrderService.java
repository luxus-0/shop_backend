package com.github.shop.admin.order;

import com.github.shop.mail.EmailNotificationForOrderStatusChange;
import com.github.shop.order.Order;
import com.github.shop.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdminOrderService {

    private final AdminOrderRepository adminOrderRepository;
    private final AdminOrderLoggerRepository adminOrderLoggerRepository;
    private final EmailNotificationForOrderStatusChange email;

    public static Map<String, String> createAdminOrderStatusesMap() {
        Map<String, String> statuses = new HashMap<>();
        for (OrderStatus value : OrderStatus.values()) {
            statuses.put(value.name(), value.getValue());
        }
        return statuses;
    }

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

    @Transactional
    public void patchOrder(Long id, Map<String, String> values) throws AdminOrderStatusNotFound {
        AdminOrder adminOrder = adminOrderRepository.findById(id).orElseThrow();
        patchValues(adminOrder, values);
    }

    private void patchValues(AdminOrder adminOrder, Map<String, String> values) throws AdminOrderStatusNotFound {
        if (values.get("orderStatus") != null) {
            processOrderStatusChanged(adminOrder, values);
        }
    }

    private void processOrderStatusChanged(AdminOrder adminOrder, Map<String, String> values) throws AdminOrderStatusNotFound {
        OrderStatus oldOrderStatus = adminOrder.getOrderStatus();
        Order newOrder = Order.builder()
                .id(adminOrder.getId())
                .orderStatus(OrderStatus.valueOf(values.get("orderStatus")))
                .build();
        if(oldOrderStatus == newOrder.getOrderStatus()){
            return;
        }
        logStatusChanged(adminOrder.getId(), oldOrderStatus, newOrder.getOrderStatus());
        email.sendEmailNotification(newOrder.getOrderStatus(), newOrder);
    }

    public void logStatusChanged(Long orderId, OrderStatus orderStatus, OrderStatus newOrderStatus) {
        adminOrderLoggerRepository.save(AdminOrderLog.builder()
                .created(now())
                .orderId(orderId)
                .description("Change order status from " + orderStatus.getValue() + " to " + newOrderStatus.getValue())
                .build());
    }
}
