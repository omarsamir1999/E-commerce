package org.ecommerce.notification_service.service;

import org.ecommerce.notification_service.entity.EmailDetails;
import org.ecommerce.notification_service.exception.EmailDetailsNotFoundException;
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
    public void updateEmailDetailsStatus(EmailDetails updatedNotification, Integer id) {
        EmailDetails existingRecord = repository.findById(id).orElse(null);

        if(existingRecord == null){
            LOGGER.info(String.valueOf(new EmailDetailsNotFoundException("record not found with id:" + id)));
            return;
        }
        existingRecord.setStatus(updatedNotification.getStatus());
        existingRecord.setReceived_at(updatedNotification.getReceived_at());

        repository.save(existingRecord);

        LOGGER.info(String.format("Email Details record updated successfully"));
    }

    @Override
    public List<EmailDetails> getFailedEmails() {
        return null;
    }
}
