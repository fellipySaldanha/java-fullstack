package com.java.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.java.domain.Client;
import com.java.domain.Order;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Order obj) {
        SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromOrder(obj);
        sendEmail(simpleMailMessage);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order obj) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(obj.getClient().getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Pedido confirmado! Código: " + obj.getId());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(obj.toString());
        return simpleMailMessage;
    }

    protected String htmlFromTemplateOrder(Order obj) {
        Context context = new Context();
        context.setVariable("order", obj);
        return templateEngine.process("email/emailOrder", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Order obj) {        
        try {
            MimeMessage mimeMsg = prepareMimeMessageFromOrder(obj);
            sendHtmlEmail(mimeMsg);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(obj);            
        }        
    }

    protected MimeMessage prepareMimeMessageFromOrder(Order obj) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(obj.getClient().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("Pedido confirmado! Código: " + obj.getId());
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplateOrder(obj), true);
        return mimeMessage;
    }
    
    @Override
	public void sendNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage simpleMailMessage = prepareNewPasswordEmail(client, newPass);
		sendEmail(simpleMailMessage);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(client.getEmail());
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setSubject("Solicitação de nova senha");
		simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
		simpleMailMessage.setText("Nova senha: " + newPass);
		return simpleMailMessage;
	}
}