package org.ecommerce.notification_service.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class EmailDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 500)
    private String message;
    private String emailTo;
    private int tries;
    private Date created_at;
    private Date received_at;
    @Enumerated(EnumType.STRING)
    private EmailStatus status;

    public EmailDetails(){
    }
    public EmailDetails(String emailTo, String message){
        this.message= message;
        this.emailTo= emailTo;
        this.created_at= new Date();
        this.tries= 0;
        this.status=EmailStatus.FAILED;
    }

    public void setTries(int retry) {
        this.tries = tries;
    }

    public void setReceived_at(Date received_at) {
        this.received_at = received_at;
    }

    public void setStatus(EmailStatus status) {
        this.status = status;
    }
    public EmailStatus getStatus() {
        return status;
    }

    public Date getReceived_at() {
        return received_at;
    }

    public int getTries() {
        return tries;
    }

    public String getMessage() {
        return message;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public Integer getId() {
        return id;
    }
}
