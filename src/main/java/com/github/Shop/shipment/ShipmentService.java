package com.github.Shop.shipment;

import com.github.Shop.address.Address;
import com.github.Shop.contact.Contact;
import com.github.Shop.shipment.dto.RecipientDto;
import com.github.Shop.shipment.dto.SenderDto;
import com.github.Shop.shipment.dto.ShipmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;

    private static Sender getSender(SenderDto sender) {
        return Sender.builder()
                .name(sender.name())
                .surname(sender.surname())
                .pesel(sender.pesel())
                .address(getAddress(sender))
                .contact(getContact(sender))
                .build();
    }

    private static Contact getContact(SenderDto sender) {
        return Contact.builder()
                .email(sender.contact().email())
                .phone(sender.contact().phone())
                .build();
    }

    private static Address getAddress(SenderDto sender) {
        return Address.builder()
                .city(sender.address().city())
                .street(sender.address().street())
                .zipCode(sender.address().zipCode())
                .build();
    }

    private static Recipient getRecipient(RecipientDto recipient) {
        return Recipient.builder()
                .name(recipient.name())
                .surname(recipient.surname())
                .pesel(recipient.pesel())
                .address(getAddress(recipient))
                .contact(getContact(recipient))
                .build();
    }

    private static Contact getContact(RecipientDto recipient) {
        return Contact.builder()
                .email(recipient.contact().email())
                .phone(recipient.contact().phone())
                .build();
    }

    private static Address getAddress(RecipientDto recipient) {
        return Address.builder()
                .city(recipient.address().city())
                .street(recipient.address().street())
                .zipCode(recipient.address().zipCode())
                .build();
    }

    Shipment createShipment(ShipmentDto shipment) {
        Shipment createShipment = Shipment.builder()
                .trackingNumber(shipment.trackingNumber())
                .status(shipment.status())
                .shippingType(shipment.shippingType())
                .deliveryDate(shipment.deliveryDate())
                .sender(getSender(shipment.sender()))
                .recipient(getRecipient(shipment.recipient()))
                .build();
        return shipmentRepository.save(createShipment);
    }

    public List<Shipment> getShipments() {
        return shipmentRepository.findAll();
    }
}
