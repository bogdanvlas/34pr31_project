package com.itstep.my_secured_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //переопределяю дефолтные механизмы аутентификации и авторизации
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()//для всех
                .antMatchers("/secured").authenticated()//для аутентифицированных
                .antMatchers("/admin_page").hasRole("ADMIN")//для админов
                .and()
                .formLogin()//использовать дефолтную форму для входа
                .and()
                .logout();//использовать дефолтную форму для выхода
    }

    //настраиваю хранение пользователей
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = passwordEncoder();

        UserDetails user1 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .roles("USER").build();

        UserDetails user2 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN").build();

        auth.inMemoryAuthentication()
                .withUser(user1)
                .withUser(user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
