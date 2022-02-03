package com.neo4j.entity.impl;

import com.neo4j.entity.Entity;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NodeEntity
public class Drug extends Entity {
    /**
     * 药品名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drug() {
    }

    private Drug(String name) {
        this.name = name;
    }


    @Relationship(type = "Product")
    private Set<DrugProduct> products;

    public void withProduct(DrugProduct drugProduct) {
        if (this.products == null) {
            this.products = new HashSet<>();
        }
        this.products.add(drugProduct);
    }

    public static Drug create(String name) {
        return new Drug(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return Objects.equals(name, drug.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
