package org.example.javangersasterixapi.service;

import org.example.javangersasterixapi.dto.AsterixCharacterDTO;
import org.example.javangersasterixapi.model.AsterixCharacter;
import org.example.javangersasterixapi.repository.AsterixCharacterRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AsterixServiceTest {

    @Test
    void getCharacters_shouldReturnEmptyList_WhenCalledWithNull() {
        AsterixCharacterRepo repo = Mockito.mock(AsterixCharacterRepo.class);
        IdService idService = Mockito.mock(IdService.class);
        AsterixService service = new AsterixService(repo, idService);
        Mockito.when(repo.findAll()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), service.getCharacters(null));
        Mockito.verify(repo).findAll();
    }

    @Test
    void getCharacters_shouldReturnEmptyList_WhenCalledWithAge() {
        AsterixCharacterRepo repo = Mockito.mock(AsterixCharacterRepo.class);
        IdService idService = Mockito.mock(IdService.class);
        AsterixService service = new AsterixService(repo, idService);
        Mockito.when(repo.findAllByAgeIsLessThanEqual(5)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), service.getCharacters(5));
        Mockito.verify(repo).findAllByAgeIsLessThanEqual(5);
    }

    @Test
    void getCharacterById_shouldReturnCharacter_WhenCalledWithId() {
        AsterixCharacterRepo repo = Mockito.mock(AsterixCharacterRepo.class);
        IdService idService = Mockito.mock(IdService.class);
        AsterixService service = new AsterixService(repo, idService);
        Mockito.when(repo.findById("id")).thenReturn(Optional.of(new AsterixCharacter("id", "name", 5, "profession")));
        assertEquals(new AsterixCharacter("id", "name", 5, "profession"), service.getCharacterById("id"));
        Mockito.verify(repo).findById("id");
    }

    @Test
    void updateCharacter_shouldReturnCharacter_WhenCalledWithNameAgeAndProfession() {
        AsterixCharacterRepo repo = Mockito.mock(AsterixCharacterRepo.class);
        IdService idService = Mockito.mock(IdService.class);
        AsterixService service = new AsterixService(repo, idService);
        Mockito.when(repo.findById("id")).thenReturn(Optional.of(new AsterixCharacter("id", "name", 5, "profession")));

        AsterixCharacter actual = service.updateCharacter("id", new AsterixCharacterDTO("name2", 6, "profession2"));
        AsterixCharacter expected = new AsterixCharacter("id", "name2", 6, "profession2");

        assertEquals(expected, actual);
        Mockito.verify(repo).save(expected);
    }

    @Test
    void deleteCharacter() {
        AsterixCharacterRepo repo = Mockito.mock(AsterixCharacterRepo.class);
        IdService idService = Mockito.mock(IdService.class);
        AsterixService service = new AsterixService(repo, idService);
        service.deleteCharacter("id");
        Mockito.verify(repo).deleteById("id");
    }
}