package com.itstep.my_secured_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MySecuredAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySecuredAppApplication.class, args);
    }

}
