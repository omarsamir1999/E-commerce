package ecommerce.order.service;

import org.springframework.web.client.RestTemplate;

public class StockHistoryService {
    private final RestTemplate restTemplate;

    public StockHistoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void decrementStock(int productId, int value) {

    }



}
