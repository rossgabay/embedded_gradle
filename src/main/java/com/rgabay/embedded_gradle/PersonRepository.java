package com.rgabay.embedded_gradle;


import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface PersonRepository extends GraphRepository<Person> {

    Person findByName(String name);

    @Query ("match (n:Person) return n")
    List<Person> getPlist();
}
