package com.github.shop.shipment.dto;

import com.github.shop.address.dto.AddressDto;
import com.github.shop.contact.dto.ContactDto;

public record RecipientDto(String name,
                           String surname,
                           String pesel,
                           AddressDto address,
                           ContactDto contact) {
}
