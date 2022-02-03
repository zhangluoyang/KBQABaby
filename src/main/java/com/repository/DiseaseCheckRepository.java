package com.repository;

import com.neo4j.entity.impl.DiseaseCheck;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiseaseCheckRepository extends Neo4jRepository<DiseaseCheck, Long> {

    List<DiseaseCheck> findByName(@Param("name") String name);

}
