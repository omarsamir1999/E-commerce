package ecommerce.order.service;

import ecommerce.order.dtos.OrderItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class StoreService {
    private final String STORE_SERVICE_BASE_URL = "http://store-service-url"; // Replace with your actual URL

    private final RestTemplate restTemplate;

    public StoreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean orderItemsExists(List<OrderItemDto> orderedItems) {
        String url = STORE_SERVICE_BASE_URL + "/checkOrderItemsExist";
        // Make a POST request to the store service endpoint to check order items existence
         ResponseEntity<Boolean> response = restTemplate.postForEntity(url, orderedItems, Boolean.class);
         return response.getBody();
    }

    public void consumeFromStock(List<OrderItemDto> orderItems) {
        String url = STORE_SERVICE_BASE_URL + "/consumeFromStock";
        // Make a POST request to the store service endpoint to consume items from stock
         restTemplate.postForEntity(url, orderItems, Void.class);
    }
}
