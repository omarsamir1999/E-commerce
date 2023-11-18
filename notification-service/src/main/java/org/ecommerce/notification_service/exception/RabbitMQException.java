package org.ecommerce.notification_service.exception;

public class RabbitMQException extends Exception{
    public RabbitMQException(String message){
        super("RabbitMQ Exception" + message);
    }
}
