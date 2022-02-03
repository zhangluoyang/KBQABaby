package com.neo4j.entity.impl;

import com.neo4j.entity.Entity;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class Symptom extends Entity {
    /**
     * 症状名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Symptom(String name) {
        this.name = name;
    }

    public Symptom() {
    }

    public static Symptom create(String name) {
        return new Symptom(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symptom symptom = (Symptom) o;
        return Objects.equals(name, symptom.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
