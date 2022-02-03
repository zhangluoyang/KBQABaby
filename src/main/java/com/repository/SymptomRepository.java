package com.repository;

import com.neo4j.entity.impl.Symptom;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SymptomRepository extends Neo4jRepository<Symptom, Long> {

    List<Symptom> findByName(@Param("name") String name);

}
