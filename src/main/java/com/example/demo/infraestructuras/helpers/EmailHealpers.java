package com.example.demo.infraestructuras.helpers;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class EmailHealpers {

    private final JavaMailSender mailSender;

    public  void sendmail(String to ,String name ,String product){

        MimeMessage mensaje=mailSender.createMimeMessage();
        String htmlContend=this.readHtmlTemplate(name,product);
        try{
            mensaje.setFrom(new InternetAddress(("carloslatino471@gmail.com")));
            mensaje.setRecipients(MimeMessage.RecipientType.TO,to);
//            mensaje.setContent(htmlContend,"text/html;charset=utf-8");
            mensaje.setContent(htmlContend, MediaType.TEXT_HTML_VALUE);
            mailSender.send(mensaje);
        }catch (MessagingException e){
            log.error("Error to send mail",e);
        }

    }

    private String readHtmlTemplate(String name ,String product){
        try(var lines= Files.lines(TEMPLATE_PATH)){
            var html=lines.collect(Collectors.joining());
            return html.replace("{name}",name).replace("product",product);

        }catch (IOException e){
            log.error("Error to send mail IOE",e);
            throw new RuntimeException();
        }
    }

    private  final Path TEMPLATE_PATH= Paths.get("src/main/resources/email/email_template.html");


}
