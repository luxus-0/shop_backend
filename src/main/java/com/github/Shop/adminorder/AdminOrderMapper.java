package com.github.Shop.adminorder;

import com.github.Shop.adminorder.dto.AdminOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AdminOrderMapper {
    public static Page<AdminOrderDto> mapToPageDtos(Page<AdminOrder> orders) {
        return new PageImpl<>(mapToDtoList(orders.getContent()));
    }

    private static List<AdminOrderDto> mapToDtoList(List<AdminOrder> content) {
        return content.stream()
                .map(AdminOrderMapper::mapToAdminOrderDto)
                .toList();
    }

    private static AdminOrderDto mapToAdminOrderDto(AdminOrder adminOrder) {
        return AdminOrderDto.builder()
                .id(adminOrder.getId())
                .orderStatus(adminOrder.getOrderStatus())
                .placeDate(adminOrder.getPlaceDate())
                .grossValue(adminOrder.getGrossValue())
                .build();
    }
}
