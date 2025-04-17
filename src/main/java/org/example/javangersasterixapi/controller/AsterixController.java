package org.example.javangersasterixapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.javangersasterixapi.dto.AsterixCharacterDTO;
import org.example.javangersasterixapi.model.AsterixCharacter;
import org.example.javangersasterixapi.service.AsterixService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor

@RestController
@RequestMapping("/asterix/characters")
public class AsterixController {

    private final AsterixService service;

    @GetMapping
    public List<AsterixCharacter> getCharacters() {
        return service.getCharacters();
    }

    @GetMapping("/{id}")
    public AsterixCharacter getCharacterById(@PathVariable String id) {
        return service.getCharacterById(id);
    }

    @PostMapping
    public void addCharacter(@RequestBody AsterixCharacterDTO character) {
        service.addCharacter(character);
    }

    @PutMapping("/{id}")
    public void updateCharacter(@PathVariable String id, @RequestBody Map<String, Object> requestBody) {
        service.updateCharacter(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id) {
        service.deleteCharacter(id);
    }
}
