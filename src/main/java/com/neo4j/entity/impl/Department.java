package com.neo4j.entity.impl;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.neo4j.entity.Entity;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NodeEntity
public class Department extends Entity {

    /**
     * 科室名称
     */
    private String name;

    public Department() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Department(String name) {
        this.name = name;
    }


    public static Department create(String name) {
        return new Department(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
