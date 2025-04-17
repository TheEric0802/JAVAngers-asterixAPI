package org.example.javangersasterixapi.repository;

import org.example.javangersasterixapi.model.AsterixCharacter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsterixCharacterRepo extends MongoRepository<AsterixCharacter, String> {
    List<AsterixCharacter> findAllByAgeIsLessThanEqual(int ageIsLessThan);
}
