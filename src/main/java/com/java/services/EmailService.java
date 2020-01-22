package com.java.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.java.domain.Client;
import com.java.domain.Order;

public interface EmailService {

    public void sendOrderConfirmationEmail(Order obj);

    public void sendEmail(SimpleMailMessage msg);
    
    public void sendOrderConfirmationHtmlEmail(Order obj);

    public void sendHtmlEmail(MimeMessage msg);
    
    public void sendNewPasswordEmail(Client cliente, String newPass);
}