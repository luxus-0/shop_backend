package com.github.Shop.shipment;

import com.github.Shop.shipment.dto.ShipmentDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;
    @PostMapping
    public ResponseEntity<Shipment> buildShipment(@RequestBody @Valid ShipmentDto shipment){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(shipmentService.createShipment(shipment));
    }
}
