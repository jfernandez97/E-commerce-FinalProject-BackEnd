package com.ecommerce.ecommercefinal.service.implementation;

import com.ecommerce.ecommercefinal.config.ApplicationProperties;
import com.ecommerce.ecommercefinal.model.document.CartItem;
import com.ecommerce.ecommercefinal.service.CartService;
import com.ecommerce.ecommercefinal.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final ApplicationProperties applicationProperties;
    private final CartService cartService;


    @Override
    public void sendEmail() {
        var message = new SimpleMailMessage();
        message.setTo("coderhouseprueba2702@gmail.com");
        message.setSubject("New user from Ecommerce project");
        message.setText("Este es un mail que se envia desde nuestro proyecto final para avisar acerca de la creacion de un nuevo usuario");
        javaMailSender.send(message);
    }
    @Override
    public void sendMailWithOrder(List<CartItem> cartItems) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
        helper.setTo("coderhouseprueba2702@gmail.com");
        helper.setSubject("New order from Ecommerce project");
        helper.setText("Este es un mail que se envia desde nuestro proyecto final para avisar acerca de la creacion de una nueva orden: "+"\n"+
                convertToHTML(cartItems),true);
        javaMailSender.send(mimeMessage);
    }

    public String convertToHTML(List<CartItem> cartItems){
        StringBuilder stringBuilder =new StringBuilder();
        var counter=1;
        stringBuilder.append("<h3>Lista de productos de la orden : </h3>\n");
        for (CartItem item:cartItems){
            stringBuilder.append("<ul>");
            stringBuilder.append("<h5>Producto: "+counter+++"\n</h5>");
            stringBuilder.append("<li>");
            stringBuilder.append("El codigo del producto es: ");
            stringBuilder.append(item.getCode());
            stringBuilder.append("</li>");
            stringBuilder.append("<li>");
            stringBuilder.append("La cantidad del producto es de: ");
            stringBuilder.append(item.getQuantity());
            stringBuilder.append("</li>");
            stringBuilder.append("</ul>");

        }
        return stringBuilder.toString();
    }

}
