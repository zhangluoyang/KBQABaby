package com.repository;

import com.neo4j.entity.impl.DrugProduct;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrugProductRepository extends Neo4jRepository<DrugProduct, Long> {

    List<DrugProduct> findByName(@Param("name") String name);

}
