package com.github.shop.domain.shipment.dto;

import com.github.shop.domain.address.dto.AddressDto;
import com.github.shop.domain.contact.dto.ContactDto;

public record RecipientDto(String name,
                           String surname,
                           String pesel,
                           AddressDto address,
                           ContactDto contact) {
}
