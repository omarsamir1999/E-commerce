package org.ecommerce.notification_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.mail.MessagingException;

public interface SendingEmailService {
    public void sendEmail(String to, String content) throws MessagingException;
    public String createEmailContent(JsonNode node);
}
