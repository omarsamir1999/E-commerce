package ecommerce.order.dto;

import ecommerce.order.helpers.ShipmentStatus;
import ecommerce.order.models.Order;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

public class ShipmentRequest {
    private String customerEmail;
    private Long orderId;
    private LocalDateTime shipmentDate;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;
    private String country;
    private String state;
    private String city;
    private String addressLine;
}
