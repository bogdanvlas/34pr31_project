package com.itstep.my_secured_app.controller;

import com.itstep.my_secured_app.model.User;
import com.itstep.my_secured_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class PageController {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

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
}
