package org.ecommerce.notification_service.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecommerce.notification_service.exception.RabbitMQException;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.io.IOException;

@Service
public class RabbitMQConsumerUtil {
    private Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumerUtil.class);

    /**
     * Converts the message body from RabbitMQ into a JsonNode.
     *
     * @param content The RabbitMQ message containing the body to be converted.
     * @return The JsonNode representing the converted message body.
     */
    public JsonNode convertMessageBodyToJsonNode(Message content){
        JsonNode node = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            node = mapper.readTree(content.getBody());
            LOGGER.info("Message body in RabbitMQ successfully converted into JsonNode");
        } catch (IOException e) {
            LOGGER.error(String.valueOf(new RabbitMQException("Failed to convert the message body in RabbitMQ to JsonNode.\n" + e.getMessage())));
        }
        return node;
    }
}
