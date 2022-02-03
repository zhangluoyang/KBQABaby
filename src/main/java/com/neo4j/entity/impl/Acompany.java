package com.neo4j.entity.impl;

import com.neo4j.entity.Entity;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class Acompany extends Entity {
    /**
     * 并发症
     */
    private String name;

    public Acompany() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Acompany(String name) {
        this.name = name;
    }

    public static Acompany create(String name) {
        return new Acompany(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acompany acompany = (Acompany) o;
        return Objects.equals(name, acompany.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
