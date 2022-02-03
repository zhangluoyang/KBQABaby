package com.repository;

import com.neo4j.entity.impl.Drug;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrugRepository extends Neo4jRepository<Drug, Long> {

    List<Drug> findByName(@Param("name") String name);

}
