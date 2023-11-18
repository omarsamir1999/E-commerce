package org.ecommerce.notification_service.consumer;

import com.fasterxml.jackson.databind.JsonNode;
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

    @Override
    public void onMessage(Message message) {
        LOGGER.info(String.format("Message received from RabbitMQ successfully.\nMessage:\n%s\n" , new String(message.getBody())));
        JsonNode messageNode = consumerUtil.convertMessageBodyToJsonNode(message);
    }
}
