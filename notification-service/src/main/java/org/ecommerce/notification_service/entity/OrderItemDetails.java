package org.ecommerce.notification_service.entity;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderItemDetails {
    public List<OrderItem> convertJsonNodeToList(JsonNode jsonNode) {
        List<OrderItem> itemList = new ArrayList<>();

        if (jsonNode.isArray()) {
            Iterator<JsonNode> elements = jsonNode.elements();
            while (elements.hasNext()) {
                JsonNode element = elements.next();
                OrderItem item = new OrderItem(
                        element.get("productName").asText(),
                        element.get("quantity").asText(),
                        element.get("price").asText()
                );
                itemList.add(item);
            }
        }
        return itemList;
    }
    public class OrderItem{
        private String productName,quantity, price;

        public OrderItem(String productName, String quantity, String price){
            this.price=price;
            this.productName=productName;
            this.quantity=quantity;
        }

        public String getQuantity() {
            return quantity;
        }

        public String getPrice() {
            return price;
        }

        public String getProductName() {
            return productName;
        }
    }
}
