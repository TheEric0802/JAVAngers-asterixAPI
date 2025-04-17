package org.example.javangersasterixapi.service;

import lombok.RequiredArgsConstructor;
import org.example.javangersasterixapi.dto.AsterixCharacterDTO;
import org.example.javangersasterixapi.model.AsterixCharacter;
import org.example.javangersasterixapi.repository.AsterixCharacterRepo;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void addCharacter(AsterixCharacter character) {
        repo.save(character);
    }

    public void updateCharacter(AsterixCharacter character) {
        repo.save(character);
    }

    public void deleteCharacter(String id) {
        repo.deleteById(id);
    }
}
