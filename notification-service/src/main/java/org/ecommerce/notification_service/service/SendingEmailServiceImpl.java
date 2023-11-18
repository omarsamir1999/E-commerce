package org.ecommerce.notification_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.ecommerce.notification_service.entity.OrderItemDetails;
import org.ecommerce.notification_service.exception.SendingEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
public class SendingEmailServiceImpl implements SendingEmailService {
    private Logger LOGGER = LoggerFactory.getLogger(SendingEmailServiceImpl.class);
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender mailSender;

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
    public void sendEmail(String to, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(to);
            helper.setSubject("Order Confirmation - Your Recent Purchase");
            helper.setFrom(new InternetAddress(from));
            helper.setText(content, true);
            mailSender.send(message);
            LOGGER.info(String.format("Mail send successfully to a customer."));
        } catch (MessagingException e) {
            LOGGER.info(new SendingEmailException(e.getMessage()).toString());
        }catch(MailSendException e){
            LOGGER.info(new SendingEmailException(e.getMessage()).toString());
        }
    }
}
