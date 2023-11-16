package ecommerce.order.dto;

import ecommerce.order.helpers.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private long orderId;
    private long shipmentId;
    private String customerEmail;
    private LocalDateTime orderDate;
    private double paidPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
