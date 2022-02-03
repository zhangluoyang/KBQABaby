package com.neo4j.entity.impl;

import com.neo4j.entity.Entity;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class CureWay extends Entity {
    /**
     * 治疗途径
     */
    private String name;

    public CureWay() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private CureWay(String name) {
        this.name = name;
    }

    public static CureWay create(String name) {
        return new CureWay(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CureWay cureWay = (CureWay) o;
        return Objects.equals(name, cureWay.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
