package com.neo4j.entity.impl;

import com.neo4j.entity.Entity;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class DrugProduct extends Entity {
    /**
     * 药品商品名称
     */
    private String name;

    public DrugProduct() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private DrugProduct(String name) {
        this.name = name;
    }

    public static DrugProduct create(String name) {
        return new DrugProduct(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrugProduct that = (DrugProduct) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
