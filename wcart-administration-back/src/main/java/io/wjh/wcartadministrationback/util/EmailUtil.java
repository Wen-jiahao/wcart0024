package io.wjh.wcartadministrationback.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public  void sendEmail(String fromEmail, String email, String hex){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject("jcart管理端管理员密码重置");
        message.setText(hex);
        mailSender.send(message);
    }
}
