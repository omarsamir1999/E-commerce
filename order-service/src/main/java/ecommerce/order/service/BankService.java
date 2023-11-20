package ecommerce.order.service;

import ecommerce.order.dtos.PaymentDetails;
import ecommerce.order.dtos.TransactionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankService {
    private final String BANK_SERVICE_BASE_URL = "http://bank-service-url"; // Replace with your actual URL

    private final RestTemplate restTemplate;

    public BankService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TransactionResponse makeTransaction(PaymentDetails paymentDetails) {
        String url = BANK_SERVICE_BASE_URL + "/processTransaction";
         ResponseEntity<TransactionResponse> response = restTemplate.postForEntity(url, paymentDetails, TransactionResponse.class);
         return response.getBody();
    }
}
