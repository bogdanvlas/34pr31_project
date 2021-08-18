package com.itstep.my_secured_app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MySecuredAppApplicationTests {

    @Test
    void myTest() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawString = "admin";
        String encodedString = passwordEncoder.encode(rawString);
        System.out.println(encodedString);
        System.out.println(passwordEncoder.matches(rawString, encodedString));
    }

}
