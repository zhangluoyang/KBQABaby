package com.repository;

import com.neo4j.entity.impl.GetWay;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GetWayRepository extends Neo4jRepository<GetWay, Long> {

    List<GetWay> findByName(@Param("name") String name);

}
