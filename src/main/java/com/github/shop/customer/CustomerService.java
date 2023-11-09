package com.github.shop.customer;

import com.github.shop.adminorder.AdminOrder;
import com.github.shop.contact.Contact;
import com.github.shop.mail.EmailNotFoundException;
import com.github.shop.order.dto.OrderDto;

import java.util.List;

import static com.github.shop.address.AddressService.getAddresses;
import static com.github.shop.contact.ContactService.getContacts;

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

    public static String readEmail(AdminOrder order) throws EmailNotFoundException {
        return order.getCustomers().stream()
                .flatMap(customer -> customer.getContacts().stream())
                .map(Contact::getEmail)
                .findAny()
                .orElseThrow(EmailNotFoundException::new);
    }
}
