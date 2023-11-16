package ecommerce.order.service;

import ecommerce.order.dto.OrderItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class ProductService {
    private final String PRODUCT_SERVICE_BASE_URL = "http://product-service-url"; // Replace with your actual URL

    private final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double calculateTotalPrice(List<OrderItemDto> orderItems) {
        String url = PRODUCT_SERVICE_BASE_URL + "/calculateTotalPrice";
        // Make a POST request to the product service endpoint to calculate total price
         ResponseEntity<Double> response = restTemplate.postForEntity(url, orderItems, Double.class);
         return response.getBody();
    }
}
