package ecommerce.order.dtos;

import lombok.Data;

@Data
public class ShipmentRequest {
    private String country;
    private String state;
    private String city;
    private String addressLine;
}
