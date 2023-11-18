package org.ecommerce.notification_service.service;

import org.ecommerce.notification_service.entity.EmailDetails;

import java.util.List;

public interface EmailDatabaseService {
    public EmailDetails addEmailInfoToDB(EmailDetails emailDetailsRecord);
    public void updateEmailDetailsStatus(EmailDetails updatedEmailDetailsRecord, Integer id);
    List<EmailDetails> getFailedEmails();
}
