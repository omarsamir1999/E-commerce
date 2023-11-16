package ecommerce.order.dto;

import ecommerce.order.helpers.TransactionStatus;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TransactionResponse {
    private Long transactionId;
    private Long orderId;
    @Enumerated
    private TransactionStatus transactionStatus;
}
