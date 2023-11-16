package com.github.shop.domain.orderrow;

import com.github.shop.domain.cart.Cart;
import com.github.shop.domain.shipment.Shipment;
import com.github.shop.domain.shipment.ShipmentMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.github.shop.domain.orderrow.OderRowMapper.mapToOrderRow;

@Service
@AllArgsConstructor
@Log4j2
public class OrderRowManager {
    private final OrderRowRepository orderRowRepository;
    public void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(cart, orderId, shipment);
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems().stream()
                .map(cartItem -> mapToOrderRow(orderId, cartItem))
                .peek(orderRowRepository::save)
                .forEach(log::info);
    }

    private void saveShipmentRow(Cart cart, Long orderId, Shipment shipment) {
        orderRowRepository.save(ShipmentMapper.mapToShipmentRow(cart, orderId, shipment));
    }
}
