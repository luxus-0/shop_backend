package com.github.shop.domain.customer;

import com.github.shop.domain.order.dto.OrderDto;

import java.util.List;

import static com.github.shop.domain.address.AddressService.getAddresses;
import static com.github.shop.domain.contact.ContactService.getContacts;

public class CustomerService {
    public static List<Customer> createCustomers(OrderDto orderDto) {
        return List.of(Customer.builder()
                .firstName(orderDto.customer().firstName())
                .lastName(orderDto.customer().lastName())
                .pesel(orderDto.customer().pesel())
                .addresses(getAddresses(orderDto))
                .contacts(getContacts(orderDto))
                .build());
    }
}
