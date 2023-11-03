package com.github.Shop.customer.dto;

import com.github.Shop.address.dto.AddressDto;
import com.github.Shop.contact.dto.ContactDto;

public record CustomerDto (String firstName, String lastName, String pesel, AddressDto address, ContactDto contact){
}
