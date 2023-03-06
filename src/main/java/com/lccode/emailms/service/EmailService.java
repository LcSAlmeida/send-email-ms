package com.lccode.emailms.service;

import com.lccode.emailms.entity.EmailEntity;
import com.lccode.emailms.entity.StatusEmail;
import com.lccode.emailms.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender emailSender;

    public void sendEmail(EmailEntity emailEntity) {
        emailEntity.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailEntity.getEmailFrom());
            message.setTo(emailEntity.getEmailTo());
            message.setSubject(emailEntity.getSubject());
            message.setText(emailEntity.getText());
            emailSender.send(message);

            emailEntity.setStatusEmail(StatusEmail.SENT);
        }catch (MailException ex) {
            emailEntity.setStatusEmail(StatusEmail.ERROR);
        }finally {
            emailRepository.save(emailEntity);
        }
    }
}
