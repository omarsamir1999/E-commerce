package ecommerce.order.api;


import ecommerce.order.dtos.OrderRequest;
import ecommerce.order.dtos.ShipmentRequest;
import ecommerce.order.models.Shipment;
import ecommerce.order.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipment")
@RequiredArgsConstructor
public class ShipmentController {
    private final ShipmentRepository shipmentRepository;

    @PostMapping
    public void createShipment(@RequestBody ShipmentRequest shipmentRequest) {
        Shipment shipment = new Shipment();
        shipment.setState(shipmentRequest.getState());
        shipment.setCity(shipmentRequest.getCity());
        shipment.setCountry(shipmentRequest.getCountry());
        shipment.setAddressLine(shipmentRequest.getAddressLine());
        shipmentRepository.save(shipment);
    }

}
