package com.repository;

import com.neo4j.entity.impl.CureWay;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CureWayRepository extends Neo4jRepository<CureWay, Long> {

    List<CureWay> findByName(@Param("name") String name);

}
