package ecommerce.order.service;

import ecommerce.order.dtos.ShipmentRequest;
import ecommerce.order.helpers.OrderStatus;
import ecommerce.order.models.Order;
import ecommerce.order.models.Shipment;
import ecommerce.order.repository.OrderRepository;
import ecommerce.order.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShipmentService {
//    private final ShipmentRepository shipmentRepository;
//    private final OrderRepository orderRepository;
//    @PostMapping("/ship")
//    public void shipOrder(@RequestBody ShipmentRequest shipmentRequest) {
//        Optional<Order> orderOptional = orderRepository.findById(shipmentRequest.getOrderId());
//        if (!orderOptional.isPresent()) {
//            throw new IllegalArgumentException("the order doesn't exist!");
//        }
//        Order order = orderOptional.get();
//        Shipment shipment = new Shipment();
//        shipment.setOrder(order);
//        shipment.setCustomerEmail(shipment.getCustomerEmail());
//        shipment.setStoreId(shipmentRequest.getStoreId());
//        shipment.setCountry(shipmentRequest.getCountry());
//        shipment.setCity(shipmentRequest.getCity());
//        shipmentRequest.setState(shipmentRequest.getState());
//        shipmentRequest.setAddressLine(shipmentRequest.getAddressLine());
//
//        shipmentRepository.save(shipment);
//        order.setStatus(OrderStatus.SHIPPING);
//        // tell the coupon service that the coupon is used for this order
//    }
}
