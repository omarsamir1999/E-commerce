package org.ecommerce.notification_service.service;

import org.ecommerce.notification_service.entity.EmailDetails;
import org.ecommerce.notification_service.repository.EmailDatabaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailDatabaseServiceImpl implements EmailDatabaseService{
    private Logger LOGGER = LoggerFactory.getLogger(EmailDatabaseServiceImpl.class);

    @Autowired
    private EmailDatabaseRepository repository;

    @Override
    public EmailDetails addEmailInfoToDB(EmailDetails emailDetails) {
        EmailDetails entity = repository.save(emailDetails);
        LOGGER.info(String.format("new Email Details record added to database successfully"));
        return entity;
    }

    @Override
    public EmailDetails updateEmailStatus(EmailDetails updatedNotification, Integer id) {
        return null;
    }

    @Override
    public List<EmailDetails> getFailedEmails() {
        return null;
    }
}
