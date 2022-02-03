package com.repository;

import com.neo4j.entity.impl.Disease;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiseaseRepository extends Neo4jRepository<Disease, Long> {

    List<Disease> findByName(@Param("name") String name);

    /**
     * 根据药名 找出药品
     */
    @Query("match(n:Drug)-[:PRODUCTS]-(m:DrugProduct)  where n.name = {name} return m.name")
    List<String> getProduct(@Param("name") String name);

    /**
     * 根据体检项 找出 疾病
     */
    @Query("match(n:DiseaseCheck) -[:DISEASE_CHECKS]-(m:Disease) where n.name = {name} return m.name")
    List<String> getCanCheckDisease(@Param("name") String name);

    /**
     * 疾病不建议吃什么
     */
    @Query("match(n:Disease)-[:NOT_EAT_FOODS]-(m:Food) where n.name = {name} return m.name")
    List<String> getNotEat(@Param("name") String name);

    /**
     * 疾病建议吃什么
     */
    @Query("match(n:Disease)-[:DO_EAT_FOOD]-(m:Food) where n.name = {name} return m.name")
    List<String> getDoEat(@Param("name") String name);

    /**
     * 疾病易得人群
     */
    @Query("match(n:Disease) where n.name = {name} return n.easyGet")
    List<String> getEasyGet(@Param("name") String name);

    /**
     * 疾病的诱因
     */
    @Query("match(n:Disease) where n.name = {name} return n.cause")
    List<String> getCause(@Param("name") String name);

    /**
     * 疾病传播
     */
    @Query("match(n:Disease)-[:GET_WAYS]-(m:GetWay) where n.name={name} return m.name")
    List<String> getWay(@Param("name") String name);

    /**
     * 疾病的并发症
     */
    @Query("match(n:Acompany)-[:ACOMPANIES]-(m:Disease) where n.name={name} return m.name")
    List<String> getAcompany(@Param("name") String name);

    /**
     * 疾病的治愈率
     */
    @Query("match(n:Disease) where n.name = {name} return n.curedProb")
    String getCureProb(@Param("name") String name);

    /**
     * 疾病的治疗时间
     */
    @Query("match(n:Disease) where n.name = {name} return n.cureLastTime")
    String getCureLastTime(@Param("name") String name);

    /**
     * 疾病的治疗方式
     */
    @Query("match(n:Disease)-[:CURE_WAYS]-(m:CureWay) where n.name= {name} return m.name")
    List<String> getCureWay(@Param("name") String name);

    /**
     * 疾病的花费
     */
    @Query("match(n:Disease) where n.name= {name} return n.costMoney")
    String getCureCost(@Param("name") String name);

    /**
     * 疾病的症状
     */
    @Query("match(n:Disease)-[:SYMPTOMS]-(m:Symptom) where n.name = {name} return m.name")
    List<String> getSymptoms(@Param("name") String name);

    /**
     * 疾病的预防措施
     */
    @Query("match(n:Disease) where n.name = {name} return n.prevent")
    String getPrevent(@Param("name") String name);

    /**
     * 疾病的检查项
     */
    @Query("match(n:Disease) -[:DISEASE_CHECKS]-(m:DiseaseCheck) where n.name = {name} return m.name")
    List<String> getCheck(@Param("name") String name);

    /**
     * 疾病用哪些药
     */
    @Query("match(n:Disease) -[:COMMON_DRUGS] -(m:Drug) where n.name = {name} return m.name")
    List<String> getCommonDrugs(@Param("name") String name);


    /**
     * 疾病推荐哪些药
     */
    @Query("match(n:Disease)-[:GOOD_GRUGS]-(m:Drug) where n.name = {name} return m.name")
    List<String> getRecommendDrugs(@Param("name") String name);

    /**
     * 症状可能的疾病
     */
    @Query("match(n:Symptom)-[:SYMPTOMS]-(m:Disease) where n.name = {name} return m.name")
    List<String> getDisease(@Param("name") String name);

}
