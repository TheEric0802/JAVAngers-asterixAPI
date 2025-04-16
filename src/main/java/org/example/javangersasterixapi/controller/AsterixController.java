package org.example.javangersasterixapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.javangersasterixapi.model.AsterixCharacter;
import org.example.javangersasterixapi.repository.AsterixCharacterRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/asterix/characters")
public class AsterixController {

    private final AsterixCharacterRepo repo;

    @GetMapping
    public List<AsterixCharacter> getCharacters() {
        return repo.findAll();
    }
}
