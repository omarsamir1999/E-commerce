package ecommerce.order.helpers;

import ecommerce.order.dtos.OrderItemDto;
import ecommerce.order.models.Order;
import ecommerce.order.models.OrderItem;
import ecommerce.order.models.Shipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationDataExtractor {

    public static Map<String, Object> extractNotificationData(Order order, Shipment shipment) {
        Map<String, Object> notificationData = new HashMap<>();

        // Extract customer details
        Map<String, Object> customerDetails = new HashMap<>();
        customerDetails.put("name", order.getCustomerEmail()); // change this to customer name
        customerDetails.put("email", order.getCustomerEmail());


        // Extract user details
        Map<String, Object> orderDetails = new HashMap<>();

        orderDetails.put("number", order.getId());
        orderDetails.put("data", order.getDate());

        List<OrderItem> orderItems = order.getOrderItemsList();
        List<Object> items = new ArrayList<>();
        for (int i = 0; i < orderItems.size(); i++) {
            Map<String, Object> orderItem = new HashMap<>();
            orderItem.put("productName", orderItems.get(i).getProductCode());
            orderItem.put("quantity", orderItems.get(i).getQuantity());
            orderItem.put("price", orderItems.get(i).getPrice());
            items.add(orderItem);
        }

        orderDetails.put("items", items);

        Map<String, Object> billing = new HashMap<>();
        billing.put("address", shipment.getAddressLine());
        billing.put("paymentMethod", "withdraw");
        billing.put("totalAmount", order.getPaidPrice());

        Map<String, Object> shipping = new HashMap<>();
        shipping.put("address", shipment.getAddressLine());

        orderDetails.put("items", items);
        orderDetails.put("billing", billing);
        orderDetails.put("shipping", shipping);


        notificationData.put("customer", customerDetails);

        notificationData.put("order", orderDetails);

        return notificationData;
    }
}
