package org.example.javangersasterixapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.javangersasterixapi.model.AsterixCharacter;
import org.example.javangersasterixapi.repository.AsterixCharacterRepo;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public AsterixCharacter getCharacterById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public void addCharacter(@RequestBody AsterixCharacter character) {
        repo.save(character);
    }

    @PutMapping
    public void updateCharacter(@RequestBody AsterixCharacter character) {
        repo.save(character);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id) {
        repo.deleteById(id);
    }
}
