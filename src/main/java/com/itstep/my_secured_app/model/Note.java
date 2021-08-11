package com.itstep.my_secured_app.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Note {
    private int id;
    private String title;
    private String description;
}

//1 - данные о заметках хранятся в БД
//2 - реализовать контроллер, обрабатывающий CRUD-операции для заметок
//3 - работать с заметками может только автоизованный пользователь