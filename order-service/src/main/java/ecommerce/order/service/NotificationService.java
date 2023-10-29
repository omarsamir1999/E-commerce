package ecommerce.order.service;

import org.springframework.scheduling.annotation.Async;

public class NotificationService {

    @Async
    public void sendNotification(String customerEmail, String orderId, String shipmentId) {

    }
}
