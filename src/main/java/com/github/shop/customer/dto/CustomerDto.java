package com.github.shop.customer.dto;

import com.github.shop.address.dto.AddressDto;
import com.github.shop.contact.dto.ContactDto;

public record CustomerDto(String firstName, String lastName, String pesel, AddressDto address, ContactDto contact) {
}
