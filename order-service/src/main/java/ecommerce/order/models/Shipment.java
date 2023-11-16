package ecommerce.order.models;

import ecommerce.order.helpers.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    @Id
    private long id;
    private String customerEmail;
    private long storeId;
    private LocalDateTime shipmentDate;

    private String country;
    private String state;
    private String city;
    private String addressLine;

    @OneToOne
    private Order order;
}
