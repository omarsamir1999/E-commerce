package ecommerce.order.dto;

import ecommerce.order.helpers.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShipmentRequest {
    private String customerEmail;
    private Long orderId;
    private Long storeId;
    private String country;
    private String state;
    private String city;
    private String addressLine;
}
