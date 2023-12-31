package com.github.shop.domain.contact;

import com.github.shop.domain.order.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    public static List<Contact> getContacts(OrderDto orderDto) {
        return List.of(Contact.builder()
                .email(orderDto.contact().email())
                .phone(orderDto.contact().phone())
                .build());
    }
}
