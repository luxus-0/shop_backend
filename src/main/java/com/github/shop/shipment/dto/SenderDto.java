package com.github.shop.shipment.dto;

import com.github.shop.address.dto.AddressDto;
import com.github.shop.contact.dto.ContactDto;

public record SenderDto(String name,
                        String surname,
                        String pesel,
                        AddressDto address,
                        ContactDto contact) {
}
