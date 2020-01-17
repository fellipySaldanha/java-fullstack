package com.java.services;

import javax.mail.internet.MimeMessage;

import com.java.domain.Order;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    public void sendOrderConfirmationEmail(Order obj);

    public void sendEmail(SimpleMailMessage msg);
    
    public void sendOrderConfirmationHtmlEmail(Order obj);

    public void sendHtmlEmail(MimeMessage msg);
}