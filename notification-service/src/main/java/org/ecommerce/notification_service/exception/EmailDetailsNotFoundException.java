package org.ecommerce.notification_service.exception;

public class EmailDetailsNotFoundException extends Exception{
    public EmailDetailsNotFoundException(String message){
        super("Email Details Not Found Exception-" + message);
    }
}
