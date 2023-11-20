package ecommerce.order.dtos;

import ecommerce.order.helpers.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {
    private String customerEmail;
    private String cardName;
    private String cardNumber;
    private String securityCode;
    private int expirationMonth;
    private int expirationYear;
    private double amount;
    private TransactionType transactionType;
}
