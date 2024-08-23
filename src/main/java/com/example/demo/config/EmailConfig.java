package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Configuración del servidor de correo
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        // Configuración de las credenciales del correo
        mailSender.setUsername("carloslatino471@gmail.com");
        mailSender.setPassword("hkmtjfaaegpodxsw");

        // Propiedades adicionales
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Añadido para Gmail
        props.put("mail.debug", "true");

        return mailSender;
    }
}
