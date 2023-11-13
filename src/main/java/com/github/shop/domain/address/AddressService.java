package com.github.shop.domain.address;

import com.github.shop.domain.order.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    public static List<Address> getAddresses(OrderDto orderDto) {
        return List.of(Address.builder()
                .street(orderDto.address().street())
                .city(orderDto.address().city())
                .zipCode(orderDto.address().zipCode())
                .build());
    }
}
