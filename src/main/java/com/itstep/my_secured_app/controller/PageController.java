package com.itstep.my_secured_app.controller;

import com.itstep.my_secured_app.model.User;
import com.itstep.my_secured_app.repository.UserRepository;
import com.itstep.my_secured_app.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Random;

@Controller
@AllArgsConstructor
public class PageController {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private MailService mailService;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/secured")
    public String securedPage() {
        return "secured";
    }

    @GetMapping("/admin_page")
    public String adminPage() {
        return "adminPage";
    }

    //страница с формой регистрации
    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    //принимаю данные пользователя
    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) {
        //проверить, свободен ли логин
        if (userRepository.findByUsername(user.getUsername()) != null) {
            //если занят, то перенаправить обратно на страницу регистрации
            return "redirect:/signup";
        }
        //если свободен, то закодировать пароль, присвоить роль
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        //записать пользователя в БД
        userRepository.save(user);
        return "redirect:/";
    }

    @PostMapping("/forgot-psw")
    public void forgotPassword(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        //найти пользователя
        User user = userRepository.findByUsername(username);
        //сгенерировать новый случайный пароль
        String newPassword = generatePassword(8);
        //отправить новый пароль на почту пользователя
        String text = "Dear " + username + ", this is your new password: " + newPassword;
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setText(text);
        mail.setTo(user.getEmail());
        mail.setFrom("34pr31");
        mail.setSubject("New password");
        mailService.sendEmail(mail);
        //закодировать и записать пароль пользователю
        newPassword = passwordEncoder.encode(newPassword);
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    public String generatePassword(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxy";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
