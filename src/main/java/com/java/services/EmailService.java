package com.java.services;

import com.java.domain.Order;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    public void sendOrderConfirmationEmail(Order obj);

    public void sendEmail(SimpleMailMessage msg);

}