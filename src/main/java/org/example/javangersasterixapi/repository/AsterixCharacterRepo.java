package org.example.javangersasterixapi.repository;

import org.example.javangersasterixapi.model.AsterixCharacter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsterixCharacterRepo extends MongoRepository<AsterixCharacter, String> {
}
