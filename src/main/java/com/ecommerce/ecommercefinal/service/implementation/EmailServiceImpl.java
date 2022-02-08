package com.ecommerce.ecommercefinal.service.implementation;

import com.ecommerce.ecommercefinal.config.ApplicationProperties;
import com.ecommerce.ecommercefinal.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final ApplicationProperties applicationProperties;


    @Override
    public void sendEmail() {
        var message = new SimpleMailMessage();
        message.setTo("coderhouseprueba2702@gmail.com");
        message.setSubject("New user from Ecommerce project");
        message.setText("Este es un mail que se envia desde nuestro proyecto final para avisar acerca de la creacion de un nuevo usuario");
        javaMailSender.send(message);
    }

}
