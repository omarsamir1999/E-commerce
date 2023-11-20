package ecommerce.order.service;

import ecommerce.order.dtos.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class ProductService {
    private final String PRODUCT_SERVICE_BASE_URL = "http://localhost:9090/product";
    private final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double calculateTotalPrice(List<OrderItemDto> orderItems) {
        String url = PRODUCT_SERVICE_BASE_URL + "/totalcost";
        // Make a POST request to the product service endpoint to calculate total price
        ResponseEntity<OrderTotalPriceResponse> response = restTemplate.postForEntity(url, orderItems, OrderTotalPriceResponse.class);
        return response.getBody().getTotalPrice();
    }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderTotalPriceResponse {
    private Double totalPrice;
}
