package com.itstep.my_secured_app.controller;

import com.itstep.my_secured_app.model.Note;
import com.itstep.my_secured_app.model.User;
import com.itstep.my_secured_app.repository.NoteRepository;
import com.itstep.my_secured_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderDsl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.security.Principal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {
    private UserRepository userRepository;

    private NoteRepository noteRepository;

    @GetMapping("/all")
    public List<EntityModel<Note>> getNotes(Principal prl) {
        User user = userRepository.findByUsername(prl.getName());
        List<Note> notes = (List<Note>) noteRepository.findAll();
        Function<Note, EntityModel<Note>> mapper = n -> {
            return EntityModel.of(
                    n,
                    linkTo(
                            methodOn(NoteController.class).getNote(n.getId()))
                            .withRel("info")
            );
        };
        List<EntityModel<Note>> results = notes.stream()
                .map(mapper).collect(Collectors.toList());
        return results;
    }


    @GetMapping("/{id}")
    public EntityModel<Note> getNote(@PathVariable int id) {
        Note note = noteRepository.findById(id).get();
        EntityModel<Note> result = EntityModel.of(
                note,
                linkTo(NoteController.class).withRel("notes")
        );
        return result;
    }
}
