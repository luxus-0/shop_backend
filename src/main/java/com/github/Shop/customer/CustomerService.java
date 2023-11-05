package com.github.Shop.customer;

import com.github.Shop.contact.Contact;
import com.github.Shop.mail.EmailNotFoundException;
import com.github.Shop.order.Order;
import com.github.Shop.order.dto.OrderDto;

import java.util.List;

import static com.github.Shop.address.AddressService.getAddresses;
import static com.github.Shop.contact.ContactService.getContacts;

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

    public static String getCustomerEmail(Order order) throws EmailNotFoundException {
        return order.getCustomers().stream()
                .flatMap(customer -> customer.getContacts().stream())
                .map(Contact::getEmail)
                .findAny()
                .orElseThrow(EmailNotFoundException::new);
    }
}
