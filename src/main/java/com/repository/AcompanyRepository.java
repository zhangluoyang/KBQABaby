package com.repository;

import com.neo4j.entity.impl.Acompany;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AcompanyRepository extends Neo4jRepository<Acompany, Long> {

    List<Acompany> findByName(@Param("name") String name);

}
