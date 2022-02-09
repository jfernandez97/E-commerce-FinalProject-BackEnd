package com.ecommerce.ecommercefinal.service;

import com.ecommerce.ecommercefinal.model.document.CartItem;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService {
    void sendEmail();
    void sendMailWithOrder(String content);
}
