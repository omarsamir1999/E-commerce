package org.ecommerce.notification_service.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import org.ecommerce.notification_service.entity.EmailDetails;
import org.ecommerce.notification_service.service.EmailDatabaseServiceImpl;
import org.ecommerce.notification_service.service.SendingEmailServiceImpl;
import org.ecommerce.notification_service.util.RabbitMQConsumerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer implements MessageListener {
    private Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
    @Autowired
    private RabbitMQConsumerUtil consumerUtil;
    @Autowired
    private SendingEmailServiceImpl sendingEmailService;
    @Autowired
    private EmailDatabaseServiceImpl emailDatabaseService;

    /**
     * This method processes the incoming message by logging its content,
     * extracting relevant information, and performing actions such as
     * adding email details to the database, creating email content,
     * , sending emails, and update email details in database after sending successfully
     *
     * @param message The incoming message from RabbitMQ.
     */
    @Override
    public void onMessage(Message message) {
        LOGGER.info(String.format("Message received from RabbitMQ successfully.%nMessage:%nre%s%n" , new String(message.getBody())));

        JsonNode messageNode = consumerUtil.convertMessageBodyToJsonNode(message);

        // Extract customer email from the message
        String customerEmail = messageNode.get("customer").get("email").asText();

        // Add email details to the database
        EmailDetails emailDetails = emailDatabaseService.addEmailInfoToDB(
                new EmailDetails(customerEmail, messageNode.toString())
        );

        // Create email content based on the message
        String emailContent = sendingEmailService.createEmailContent(messageNode);

        // Send the email to the customer
        sendingEmailService.sendEmail(customerEmail, emailContent);

        // Update the status of email details in the database
        emailDatabaseService.updateEmailDetailsStatus(emailDetails, emailDetails.getId());
    }

}
