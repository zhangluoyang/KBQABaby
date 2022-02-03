package com.neo4j.entity.impl;

import com.neo4j.entity.Entity;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class GetWay extends Entity {
    /**
     * 传染途径
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GetWay() {
    }

    private GetWay(String name) {
        this.name = name;
    }

    public static GetWay create(String name) {
        return new GetWay(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetWay getWay = (GetWay) o;
        return Objects.equals(name, getWay.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
