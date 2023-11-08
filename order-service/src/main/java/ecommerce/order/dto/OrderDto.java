package ecommerce.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private LocalDateTime date;
    private double price;
    private String couponCode;
    private double couponValue;
    private double paidPrice;
    private String customerEmail;

    private List<OrderItemDto> orderItemDtosList;

}