package com.itstep.my_secured_app.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String role;
}


//форма регистрации - логин, пароль, подтвердить
//отправить данные пользователя ПОСТ запросом в контроллер
//------------------------------вынести в класс UserService
//проверить, свободен ли логин
//если занят, то перенаправить обратно на страницу регистрации
//если свободен, то закодировать пароль, присвоить роль
//записать пользователя в БД