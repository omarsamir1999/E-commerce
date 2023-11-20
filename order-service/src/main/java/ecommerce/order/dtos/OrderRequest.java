package ecommerce.order.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    // order details
    private List<OrderItemDto> orderItemDtosList;
    private String customerEmail;
    private String couponCode;

    // shipment details
    private String country;
    private String state;
    private String city;
    private String addressLine;

    // payment details
    private String cardName;
    private String cardNumber;
    private String securityCode;
    private int expirationMonth;
    private int expirationYear;
}