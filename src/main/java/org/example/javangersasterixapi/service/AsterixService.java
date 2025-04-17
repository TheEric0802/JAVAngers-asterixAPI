package org.example.javangersasterixapi.service;

import lombok.RequiredArgsConstructor;
import org.example.javangersasterixapi.dto.AsterixCharacterDTO;
import org.example.javangersasterixapi.model.AsterixCharacter;
import org.example.javangersasterixapi.repository.AsterixCharacterRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service @RequiredArgsConstructor
public class AsterixService {

    private final AsterixCharacterRepo repo;
    private final IdService idService;

    public List<AsterixCharacter> getCharacters() {
        return repo.findAll();
    }

    public AsterixCharacter getCharacterById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void addCharacter(AsterixCharacterDTO character) {
        repo.save(new AsterixCharacter(idService.randomID(), character.getName(), character.getAge(), character.getProfession()));
    }

    public void updateCharacter(String id, Map<String, Object> requestBody) throws NoSuchElementException {
        AsterixCharacter character = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID " + id + " not found"));
        if (requestBody.containsKey("name")) {
            character.setName((String) requestBody.get("name"));
        }
        if (requestBody.containsKey("age")) {
            character.setAge((int) requestBody.get("age"));
        }
        if (requestBody.containsKey("profession")) {
            character.setProfession((String) requestBody.get("profession"));
        }
        repo.save(character);
    }

    public void deleteCharacter(String id) {
        repo.deleteById(id);
    }
}
