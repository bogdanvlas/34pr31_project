package com.itstep.my_secured_app;

import com.itstep.my_secured_app.service.MailService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MySecuredAppApplicationTests {
    private MailService mailService;

    @Autowired
    public MySecuredAppApplicationTests(MailService mailService) {
        this.mailService = mailService;
    }

    @Test
    void mailSendingTest() {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("sender@mail.com");
        mail.setTo("receiver@mail.com");
        mail.setSubject("Test functionality");
        mail.setText("This message will be received by test user");
        System.out.println("Start sending...");
        for (int i = 0; i < 3; i++) {
            mailService.sendEmail(mail);
        }
        System.out.println("End sending.");
    }

    @Test
    void myTest() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawString = "admin";
        String encodedString = passwordEncoder.encode(rawString);
        System.out.println(encodedString);
        System.out.println(passwordEncoder.matches(rawString, encodedString));
    }

}
