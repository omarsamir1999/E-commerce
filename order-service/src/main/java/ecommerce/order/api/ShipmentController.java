package ecommerce.order.api;

import ecommerce.order.dto.ShipmentRequest;
import ecommerce.order.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipment")
@RequiredArgsConstructor
public class ShipmentController {
    private final ShipmentService shipmentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createShipment(@RequestBody ShipmentRequest shipmentRequest) {
        shipmentService.createShipment(shipmentRequest);
    }
}
