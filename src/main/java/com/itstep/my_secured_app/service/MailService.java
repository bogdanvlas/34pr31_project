package com.itstep.my_secured_app.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmail(SimpleMailMessage mail) {
        System.out.println(Thread.currentThread().getName());
        javaMailSender.send(mail);
    }
}
//пользователь вводит логин и нажимает кнопку "Забыл пароль"
//генерируется случайный пароль
//новый пароль кодируется, записывается в базу для этого пользователя
//новый пароль отправляется на почту пользователю
