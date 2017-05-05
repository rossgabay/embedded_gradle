
package com.rgabay.embedded_gradle;

import java.util.*;
import java.util.stream.Collectors;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Person {

    private Person() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public Person(String name) {
        this.name = name;
    }

	@GraphId
    private Long id;

    public Long getId() {
        return id;
    }

    @Property
	private String name;

    @Relationship(type="favorite_pet", direction = Relationship.OUTGOING)
    private Pet favorite_pet;

    void setFavorite_pet(Pet favorite_pet) {
        this.favorite_pet = favorite_pet;
    }


    @Relationship(type="has_pet", direction = Relationship.OUTGOING)
    Set<Pet> pets;

    void hasPet(Pet pet) {
        if (pets == null) {
            pets = new HashSet<>();
        }
        pets.add(pet);
    }

	@Relationship(type = "TEAMMATE", direction = Relationship.UNDIRECTED)
    Set<Person> teammates;

	public void worksWith(Person person) {
		if (teammates == null) {
			teammates = new HashSet<>();
		}
		teammates.add(person);
	}

	public String toString() {

		return this.name + "'s teammates => "
				+ Optional.ofNullable(this.teammates).orElse(
						Collections.emptySet()).stream().map(
								person -> person.getName()).collect(Collectors.toList());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
