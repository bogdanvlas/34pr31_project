package com.itstep.my_secured_app.controller;

import com.itstep.my_secured_app.model.Note;
import com.itstep.my_secured_app.model.User;
import com.itstep.my_secured_app.repository.NoteRepository;
import com.itstep.my_secured_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {
    private UserRepository userRepository;

    private NoteRepository noteRepository;

    @GetMapping
    public String notesPage(Model model, Principal prl) {
        String username = prl.getName();
        User user = userRepository.findByUsername(username);
        Iterable<Note> notes = user.getNotes();
        model.addAttribute("notes", notes);
        return "notesPage";
    }
}

//доделать операции NoteController в привязке к конкретному пользователю

//добавить возможность изменения пароля для авторизованного пользователя
