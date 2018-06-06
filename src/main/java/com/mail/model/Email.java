package com.mail.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "email")
@Data
@NoArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMail;
    private String keyMail;
    private String addressSender;
    private String addressReceiver;
    private String dateSend;
    private String subjectMail;
    private String bodyMail;
    private EmailMode emailMode;
    private int markedMail;

    public Email(String keyMail, String addressSender, String addressReceiver, String subjectMail, String bodyMail) {
        this.keyMail = keyMail;
        this.addressSender = addressSender;
        this.addressReceiver = addressReceiver;
        this.subjectMail = subjectMail;
        this.bodyMail = bodyMail;
    }

    public Email(Email email, EmailMode emailMode) {
        this.keyMail = email.keyMail;
        this.addressSender = email.addressSender;
        this.addressReceiver = email.addressReceiver;
        this.dateSend = email.dateSend;
        this.subjectMail = email.subjectMail;
        this.bodyMail = email.bodyMail;
        this.emailMode = emailMode;
        this.markedMail = email.markedMail;
    }
}
