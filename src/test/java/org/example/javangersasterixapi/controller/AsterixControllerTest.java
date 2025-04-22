package org.example.javangersasterixapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.javangersasterixapi.model.AsterixCharacter;
import org.example.javangersasterixapi.repository.AsterixCharacterRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AsterixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AsterixCharacterRepo repo;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getCharacters_shouldReturnListOfOneCharacter_whenCalled() throws Exception {
        AsterixCharacter character = new AsterixCharacter("1", "name", 5, "profession");
        repo.save(character);

        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                    [
                        {
                            "id": "1",
                            "name": "name",
                            "age": 5,
                            "profession": "profession"
                        }
                    ]
                """));
    }

    @Test
    void getCharacterById_shouldReturnCharacter_WhenCalledWithId() throws Exception {
        AsterixCharacter character = new AsterixCharacter("2", "name", 5, "profession");
        repo.save(character);

        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                    {
                        "id": "2",
                        "name": "name",
                        "age": 5,
                        "profession": "profession"
                    }
                """));
    }

    @Test
    void addCharacter_shouldReturnCharacter_WhenCalledWithNameAgeAndProfession() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/asterix/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "name": "name",
                                        "age": 5,
                                        "profession": "profession"
                                    }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                            {
                                "name": "name",
                                "age": 5,
                                "profession": "profession"
                            }
                        """))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        AsterixCharacter character = objectMapper.readValue(json, AsterixCharacter.class);

        assertEquals(new AsterixCharacter(character.getId(), "name", 5, "profession"), repo.findById(character.getId()).orElse(null));
    }

    @Test
    void updateCharacter_shouldReturnCharacter_WhenCalledWithAgeAndProfession() throws Exception {
        AsterixCharacter character = new AsterixCharacter("3", "name", 5, "profession");
        repo.save(character);

        mockMvc.perform(MockMvcRequestBuilders.put("/asterix/characters/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "age": 15,
                        "profession": "profession2"
                    }
                """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                    {
                        "id": "3",
                        "name": "name",
                        "age": 15,
                        "profession": "profession2"
                    }
                """));

        assertEquals(new AsterixCharacter("3", "name", 15, "profession2"), repo.findById("3").orElse(null));
    }

    @Test
    void deleteCharacter_shouldReturnStatusOK_WhenCalledWithId() throws Exception {
        AsterixCharacter character = new AsterixCharacter("4", "name", 5, "profession");
        repo.save(character);

        mockMvc.perform(MockMvcRequestBuilders.delete("/asterix/characters/4"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        assertNull(repo.findById("4").orElse(null));
    }

}