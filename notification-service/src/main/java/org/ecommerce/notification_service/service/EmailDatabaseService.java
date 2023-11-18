package org.ecommerce.notification_service.service;

import org.ecommerce.notification_service.entity.EmailDetails;

import java.util.List;

public interface EmailDatabaseService {
    public EmailDetails addEmailInfoToDB(EmailDetails notification);
    public EmailDetails updateEmailStatus(EmailDetails updatedNotification, Integer id);
    List<EmailDetails> getFailedEmails();
}
