package org.ecommerce.notification_service.repository;

import org.ecommerce.notification_service.entity.EmailDetails;
import org.ecommerce.notification_service.entity.EmailStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping
public interface EmailDatabaseRepository extends JpaRepository<EmailDetails,Integer> {
    List<EmailDetails> findByTriesLessThanAndStatus(int tries, EmailStatus status);
}
