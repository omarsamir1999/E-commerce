package org.ecommerce.notification_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.mail.MessagingException;

public interface EmailService {
    public void sendEmail(String to, String content) throws MessagingException;
    public String createEmailContent(JsonNode node);
}
