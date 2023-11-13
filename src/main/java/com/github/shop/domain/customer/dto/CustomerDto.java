package com.github.shop.domain.customer.dto;

import com.github.shop.domain.address.dto.AddressDto;
import com.github.shop.domain.contact.dto.ContactDto;

public record CustomerDto(String firstName, String lastName, String pesel, AddressDto address, ContactDto contact) {
}
