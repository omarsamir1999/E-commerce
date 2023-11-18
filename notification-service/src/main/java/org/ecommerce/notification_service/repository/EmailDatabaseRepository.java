package org.ecommerce.notification_service.repository;

import org.ecommerce.notification_service.entity.EmailDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping
public interface EmailDatabaseRepository extends JpaRepository<EmailDetails,Integer> {
}
