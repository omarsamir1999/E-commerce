package org.ecommerce.notification_service.exception;

public class SendingEmailException extends Exception{
    public SendingEmailException(String message){
        super("Email Exception-" + message);
    }
}
