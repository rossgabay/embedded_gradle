package com.rgabay.embedded_gradle;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by rossgabay on 5/3/17.
 */
@NodeEntity
public class Pet {

    public Pet(String name){this.name = name;}

    @GraphId
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Property(name="name")
    private String name;

    private Pet(){}
}
