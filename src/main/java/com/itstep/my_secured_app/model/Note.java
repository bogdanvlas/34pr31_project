package com.itstep.my_secured_app.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}


//1 - добавить в класс Заметка поля: время создания и время последней модификации
