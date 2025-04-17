package org.example.javangersasterixapi.service;

import lombok.RequiredArgsConstructor;
import org.example.javangersasterixapi.dto.AsterixCharacterDTO;
import org.example.javangersasterixapi.model.AsterixCharacter;
import org.example.javangersasterixapi.repository.AsterixCharacterRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service @RequiredArgsConstructor
public class AsterixService {

    private final AsterixCharacterRepo repo;
    private final IdService idService;

    public List<AsterixCharacter> getCharacters(Integer age) {
        List<AsterixCharacter> result;
        if (age != null) {
            result = repo.findAllByAgeIsLessThanEqual(age);
        } else {
            result = repo.findAll();
        }
        return result;
    }

    public AsterixCharacter getCharacterById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void addCharacter(AsterixCharacterDTO character) {
        repo.save(new AsterixCharacter(idService.randomID(), character.getName(), character.getAge(), character.getProfession()));
    }

    public void updateCharacter(String id, AsterixCharacterDTO characterDTO) throws NoSuchElementException {
        AsterixCharacter character = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID " + id + " not found"));
        if (characterDTO.getName() != null) {
            character.setName(characterDTO.getName());
        }
        if (characterDTO.getAge() != null) {
            character.setAge(characterDTO.getAge());
        }
        if (characterDTO.getProfession() != null) {
            character.setProfession(characterDTO.getProfession());
        }
        repo.save(character);
    }

    public void deleteCharacter(String id) {
        repo.deleteById(id);
    }
}
