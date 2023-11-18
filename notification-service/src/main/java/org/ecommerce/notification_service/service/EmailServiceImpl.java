package org.ecommerce.notification_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.mail.MessagingException;
import org.ecommerce.notification_service.entity.OrderItemDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService{
    private Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Autowired
    private TemplateEngine templateEngine;
    @Override
    public String createEmailContent(JsonNode node) {
        Context context = new Context();
        context.setVariable("customerName", node.get("customer").get("name").asText());
        context.setVariable("orderNumber", node.get("order").get("number").asText());
        context.setVariable("orderDate", node.get("order").get("date").asText());
        context.setVariable("billingAddress", node.get("order").get("billing").get("address").asText());
        context.setVariable("paymentMethod", node.get("order").get("billing").get("paymentMethod").asText());
        context.setVariable("totalAmount", node.get("order").get("billing").get("totalAmount").asText());
        context.setVariable("shippingAddress", node.get("order").get("shipping").get("address").asText());
        List<OrderItemDetails.OrderItem> items = new OrderItemDetails().convertJsonNodeToList(node.get("order").get("items"));
        context.setVariable("items", items);
        String emailContent = templateEngine.process("emailTemplate.html", context);
        LOGGER.info(String.format("Message converted from JsonNode to existing email template to be ready for sending."));
        return emailContent;
    }
    @Override
    public void sendEmail(String to, String content) throws MessagingException {

    }
}
