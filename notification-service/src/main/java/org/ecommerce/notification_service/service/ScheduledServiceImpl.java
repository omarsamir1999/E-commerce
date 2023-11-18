package org.ecommerce.notification_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.ecommerce.notification_service.entity.EmailDetails;
import org.ecommerce.notification_service.entity.EmailStatus;
import org.ecommerce.notification_service.exception.SendingEmailException;
import org.ecommerce.notification_service.repository.EmailDatabaseRepository;
import org.ecommerce.notification_service.util.RabbitMQConsumerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledServiceImpl implements ScheduledService{
    private Logger LOGGER = LoggerFactory.getLogger(ScheduledServiceImpl.class);
    @Autowired
    private EmailDatabaseRepository repository;
    @Autowired
    private EmailDatabaseServiceImpl emailDatabaseService;
    @Autowired
    private RabbitMQConsumerUtil rabbitMQUtil;
    @Autowired
    private SendingEmailServiceImpl sendingEmailService;

    /**
     * Asynchronous scheduled task to resend failed email notifications.
     *
     * This method execute asynchronously, and it is scheduled to run daily
     * at midnight using the cron expression.
     * The method retrieves failed email records from the database based on
     * status is failed and tries is less than 3, processes each record,
     * and attempts to resend the email. If the resend is successful,
     * the email status is updated to EmailStatus.SUCCESS.
     * If the resend fails, the email status is updated to EmailStatus.FAILED
     * and in each case, the records in database updated.
     *
     * @throws MailSendException If there is an exception while sending the email.
     *                           In case of failure, the email status is updated to FAILED in database.
     */
    @Async
    @Scheduled(cron = "0 0 0 * * *")
    public void resendFailedNotifications() {
        LOGGER.info(String.format("Scheduled Task started...\nResend Failed Emails..."));
        EmailDetails emailDetailsRecord = null;
        try{
            for (EmailDetails record : emailDatabaseService.getFailedEmails()) {

                emailDetailsRecord = record;

                JsonNode node = rabbitMQUtil.ConvertStringToJsonNode(emailDetailsRecord.getMessage());

                String emailContent = sendingEmailService.createEmailContent(node);

                sendingEmailService.sendEmail(emailDetailsRecord.getEmailTo(), emailContent);

                updateFailedEmailsInDB(emailDetailsRecord, EmailStatus.SUCCESS);

                LOGGER.info(String.format("Scheduled Task completed"));
            }
        } catch (MailSendException e) {
            updateFailedEmailsInDB(emailDetailsRecord, EmailStatus.FAILED);
            LOGGER.info(String.format(new SendingEmailException("Email failed to send to the customer again") + e.getMessage()));
        }
    }

    private void updateFailedEmailsInDB(EmailDetails record, EmailStatus status){
        record.setTries(record.getTries() + 1);
        record.setStatus(EmailStatus.SUCCESS);
        record.setReceived_at(new Date());
        emailDatabaseService.updateEmailDetailsStatus(record, record.getId());
    }
}
