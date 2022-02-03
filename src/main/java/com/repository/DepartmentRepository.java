package com.repository;

import com.neo4j.entity.impl.Department;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends Neo4jRepository<Department, Long> {

    List<Department> findByName(@Param("name") String name);

}
