package com.neo4j.entity.impl;

import com.neo4j.entity.Entity;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class DiseaseCheck extends Entity {

    /**
     * 检查项目名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiseaseCheck() {
    }

    private DiseaseCheck(String name) {
        this.name = name;
    }

    public static DiseaseCheck create(String name) {
        return new DiseaseCheck(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiseaseCheck that = (DiseaseCheck) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
