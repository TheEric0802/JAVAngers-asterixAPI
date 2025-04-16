package org.example.javangersasterixapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AsterixCharacter {

    String id, name;
    int age;
    String profession;
}
