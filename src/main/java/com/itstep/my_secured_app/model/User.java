package com.itstep.my_secured_app.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @Column(unique = true)
    private String username;
    private String password;
    //вынести роль в отдельную сущность
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    @Column(unique = true)
    private String email;
    private boolean enabled;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Note> notes;
}


//форма регистрации - логин, пароль, подтвердить
//отправить данные пользователя ПОСТ запросом в контроллер
//------------------------------вынести в класс UserService
//проверить, свободен ли логин
//если занят, то перенаправить обратно на страницу регистрации
//если свободен, то закодировать пароль, присвоить роль
//записать пользователя в БД