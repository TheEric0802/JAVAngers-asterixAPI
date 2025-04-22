package org.example.javangersasterixapi.service;

import org.example.javangersasterixapi.dto.AsterixCharacterDTO;
import org.example.javangersasterixapi.model.AsterixCharacter;
import org.example.javangersasterixapi.repository.AsterixCharacterRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsterixServiceTest {

    @Test
    void getCharacters_shouldReturnEmptyList_WhenCalledWithNull() {
        AsterixCharacterRepo repo = mock(AsterixCharacterRepo.class);
        IdService idService = mock(IdService.class);
        AsterixService service = new AsterixService(repo, idService);
        when(repo.findAll()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), service.getCharacters(null));
        verify(repo, times(1)).findAll();
        verifyNoMoreInteractions(repo);
    }

    @Test
    void getCharacters_shouldReturnEmptyList_WhenCalledWithAge() {
        AsterixCharacterRepo repo = mock(AsterixCharacterRepo.class);
        IdService idService = mock(IdService.class);
        AsterixService service = new AsterixService(repo, idService);
        when(repo.findAllByAgeIsLessThanEqual(5)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), service.getCharacters(5));
        verify(repo, times(1)).findAllByAgeIsLessThanEqual(5);
        verifyNoMoreInteractions(repo);
    }

    @Test
    void getCharacterById_shouldReturnCharacter_WhenCalledWithId() {
        AsterixCharacterRepo repo = mock(AsterixCharacterRepo.class);
        IdService idService = mock(IdService.class);
        AsterixService service = new AsterixService(repo, idService);
        when(repo.findById("id")).thenReturn(Optional.of(new AsterixCharacter("id", "name", 5, "profession")));
        assertEquals(new AsterixCharacter("id", "name", 5, "profession"), service.getCharacterById("id"));
        verify(repo, times(1)).findById("id");
        verifyNoMoreInteractions(repo);
    }

    @Test
    void updateCharacter_shouldReturnCharacter_WhenCalledWithNameAgeAndProfession() {
        AsterixCharacterRepo repo = mock(AsterixCharacterRepo.class);
        IdService idService = mock(IdService.class);
        AsterixService service = new AsterixService(repo, idService);
        when(repo.findById("id")).thenReturn(Optional.of(new AsterixCharacter("id", "name", 5, "profession")));

        AsterixCharacter actual = service.updateCharacter("id", new AsterixCharacterDTO("name2", 6, "profession2"));
        AsterixCharacter expected = new AsterixCharacter("id", "name2", 6, "profession2");

        assertEquals(expected, actual);
        verify(repo, times(1)).save(expected);
    }

    @Test
    void deleteCharacter_shouldUseDeleteById() {
        AsterixCharacterRepo repo = mock(AsterixCharacterRepo.class);
        IdService idService = mock(IdService.class);
        AsterixService service = new AsterixService(repo, idService);
        service.deleteCharacter("id");
        verify(repo, times(1)).deleteById("id");
        verifyNoMoreInteractions(repo);
    }

    @Test
    void addCharacter_shouldReturnCharacter() {
        AsterixCharacterRepo repo = mock(AsterixCharacterRepo.class);
        IdService idService = mock(IdService.class);
        AsterixService service = new AsterixService(repo, idService);
        when(idService.randomID()).thenReturn("id");

        AsterixCharacter actual = service.addCharacter(new AsterixCharacterDTO("name", 5, "profession"));

        AsterixCharacter expected = new AsterixCharacter("id", "name", 5, "profession");

        assertEquals(expected, actual);
        verify(repo, times(1)).save(expected);
        verifyNoMoreInteractions(repo);
    }
}