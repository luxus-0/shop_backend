package com.github.shop.shipment;

import com.github.shop.shipment.dto.ShipmentDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @GetMapping
    public ResponseEntity<List<Shipment>> readShipments() {
        return ResponseEntity.status(OK)
                .body(shipmentService.getShipments());
    }

    @PostMapping
    public ResponseEntity<Shipment> buildShipment(@RequestBody @Valid ShipmentDto shipment) {
        return ResponseEntity.status(CREATED)
                .body(shipmentService.createShipment(shipment));
    }
}
