package com.itstep.my_secured_app;

import com.itstep.my_secured_app.model.Event;
import com.itstep.my_secured_app.model.Subscription;
import com.itstep.my_secured_app.model.User;
import com.itstep.my_secured_app.repository.EventRepository;
import com.itstep.my_secured_app.repository.SubscriptionRepository;
import com.itstep.my_secured_app.repository.UserRepository;
import com.itstep.my_secured_app.service.MailService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootTest
class MySecuredAppApplicationTests {
    private MailService mailService;

    private UserRepository userRepository;

    private EventRepository eventRepository;

    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public MySecuredAppApplicationTests(MailService mailService, UserRepository userRepository, EventRepository eventRepository, SubscriptionRepository subscriptionRepository) {
        this.mailService = mailService;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.subscriptionRepository = subscriptionRepository;
    }


    @Test
    void testSubscription(){
        User user = userRepository.findByUsername("kirill");
        Event event = eventRepository.findById(1).get();
        user.subscribe(event);
        userRepository.save(user);
        Subscription subscription = subscriptionRepository.findByUserIdAndEventId(user.getId(), event.getId());
        subscription.setSubscriptionDate(new Date());
        subscriptionRepository.save(subscription);
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
