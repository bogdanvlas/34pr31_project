package com.itstep.my_secured_app.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    @Column(unique = true)
    private String email;
    private boolean enabled;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Note> notes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Event> events = new HashSet<>();


    public void subscribe(Event event) {
        this.events.add(event);
        event.getSubscribers().add(this);
    }

    public void addRole(Role role){
        this.roles.add(role);
        role.getUsers().add(this);
    }
}


//форма регистрации - логин, пароль, подтвердить
//отправить данные пользователя ПОСТ запросом в контроллер
//------------------------------вынести в класс UserService
//проверить, свободен ли логин
//если занят, то перенаправить обратно на страницу регистрации
//если свободен, то закодировать пароль, присвоить роль
//записать пользователя в БД