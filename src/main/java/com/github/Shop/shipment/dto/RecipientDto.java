package com.github.Shop.shipment.dto;

import com.github.Shop.address.dto.AddressDto;
import com.github.Shop.contact.dto.ContactDto;

public record RecipientDto(String name,
                           String surname,
                           String pesel,
                           AddressDto address,
                           ContactDto contact) {
}
