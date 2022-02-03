package com.repository;

import com.neo4j.entity.impl.Food;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends Neo4jRepository<Food, Long> {

    List<Food> findByName(@Param("name") String name);

}
