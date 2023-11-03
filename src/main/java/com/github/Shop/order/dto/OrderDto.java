package com.github.Shop.order.dto;

import com.github.Shop.address.dto.AddressDto;
import com.github.Shop.contact.dto.ContactDto;
import com.github.Shop.customer.dto.CustomerDto;
import com.github.Shop.order.OrderStatus;

import java.time.LocalDateTime;

public record OrderDto(Long cartId, CustomerDto customer, ContactDto contact, AddressDto address, LocalDateTime placeDate, OrderStatus orderStatus) {
}
