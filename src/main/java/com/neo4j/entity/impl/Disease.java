package com.neo4j.entity.impl;

import com.neo4j.entity.Entity;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NodeEntity
public class Disease extends Entity {
    /**
     * 疾病名称
     */
    private String name;

    /**
     * 预防措施
     */
    private String prevent;

    /**
     * 诱因
     */
    private String cause;

    /**
     * 医保
     */
    private String yibao;

    /**
     * 得病率
     */
    private String getProb;

    /**
     * 易得病群体
     */
    private String easyGet;

    /**
     * 治疗时间
     */
    private String cureLastTime;

    /**
     * 治愈率
     */
    private String curedProb;

    /**
     * 花费
     */
    private String costMoney;

    public Disease() {
    }

    private Disease(String name,
                    String prevent,
                    String cause,
                    String yibao,
                    String getProb,
                    String easyGet,
                    String cureLastTime,
                    String curedProb,
                    String costMoney) {
        this.name = name;
        this.prevent = prevent;
        this.cause = cause;
        this.yibao = yibao;
        this.getProb = getProb;
        this.easyGet = easyGet;
        this.cureLastTime = cureLastTime;
        this.curedProb = curedProb;
        this.costMoney = costMoney;
    }

    /**
     * 推荐食物
     */
    @Relationship(type = "RecommandEatFood")
    private Set<Food> recommandFoods;

    public void withRecommandFood(Food food) {
        if (this.recommandFoods == null) {
            this.recommandFoods = new HashSet<>();
        }
        this.recommandFoods.add(food);
    }

    /**
     * 忌吃食物
     */
    @Relationship(type = "NotEatFood")
    private Set<Food> notEatFoods;

    public void withNotEatFood(Food food) {
        if (this.notEatFoods == null) {
            this.notEatFoods = new HashSet<>();
        }
        this.notEatFoods.add(food);
    }

    /**
     * 宜吃食物
     */
    @Relationship(type = "DoEatFood")
    private Set<Food> doEatFoods;

    public void withDoEatFood(Food food) {
        if (this.doEatFoods == null) {
            this.doEatFoods = new HashSet<>();
        }
        this.doEatFoods.add(food);
    }

    /**
     * 处理的科室
     */
    @Relationship(type = "DealWithDepartment")
    private Set<Department> dealWithDepartments;

    public void withDepartMent(Department departMent) {
        if (this.dealWithDepartments == null) {
            this.dealWithDepartments = new HashSet<>();
        }
        this.dealWithDepartments.add(departMent);
    }

    /**
     * 常用药品
     */
    @Relationship(type = "CommonDrug")
    private Set<Drug> commonDrugs;

    public void withCommonDrugs(Drug drug) {
        if (this.commonDrugs == null) {
            this.commonDrugs = new HashSet<>();
        }
        this.commonDrugs.add(drug);
    }

    /**
     * 好评药品
     */
    @Relationship(type = "RecommandDrug")
    private Set<Drug> goodGrugs;

    public void withRecommandDrug(Drug drug) {
        if (this.goodGrugs == null) {
            this.goodGrugs = new HashSet<>();
        }
        this.goodGrugs.add(drug);
    }

    /**
     * 检查项目
     */
    @Relationship(type = "Check")
    private Set<DiseaseCheck> diseaseChecks;

    public void withChecks(DiseaseCheck diseaseCheck) {
        if (this.diseaseChecks == null) {
            this.diseaseChecks = new HashSet<>();
        }
        this.diseaseChecks.add(diseaseCheck);
    }

    /**
     * 症状
     */
    @Relationship(type = "Symptom")
    private Set<Symptom> symptoms;

    public void withSymptom(Symptom symptom) {
        if (this.symptoms == null) {
            this.symptoms = new HashSet<>();
        }
        this.symptoms.add(symptom);
    }

    /**
     * 并发症
     */
    @Relationship(type = "Acompany")
    private Set<Acompany> acompanies;

    public void withAcompany(Acompany acompany) {
        if (this.acompanies == null) {
            this.acompanies = new HashSet<>();
        }
        this.acompanies.add(acompany);
    }

    /**
     * 得病途径
     */
    @Relationship(type = "GetWay")
    private Set<GetWay> getWays;

    public void withGetWay(GetWay getWay) {
        if (this.getWays == null) {
            this.getWays = new HashSet<>();
        }
        this.getWays.add(getWay);
    }

    /**
     * 治疗途径
     */
    @Relationship(type = "CureWay")
    private Set<CureWay> cureWays;

    public void withCureWay(CureWay cureWay) {
        if (this.cureWays == null) {
            this.cureWays = new HashSet<>();
        }
        this.cureWays.add(cureWay);
    }


    public static Disease create(String name,
                                 String prevent,
                                 String cause,
                                 String yibao,
                                 String getProb,
                                 String easyGet,
                                 String cureLastTime,
                                 String curedProb,
                                 String costMoney) {
        return new Disease(name, prevent, cause, yibao, getProb, easyGet, cureLastTime, curedProb, costMoney);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrevent() {
        return prevent;
    }

    public void setPrevent(String prevent) {
        this.prevent = prevent;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getYibao() {
        return yibao;
    }

    public void setYibao(String yibao) {
        this.yibao = yibao;
    }

    public String getGetProb() {
        return getProb;
    }

    public void setGetProb(String getProb) {
        this.getProb = getProb;
    }

    public String getEasyGet() {
        return easyGet;
    }

    public void setEasyGet(String easyGet) {
        this.easyGet = easyGet;
    }


    public String getCureLastTime() {
        return cureLastTime;
    }

    public void setCureLastTime(String cureLastTime) {
        this.cureLastTime = cureLastTime;
    }

    public String getCuredProb() {
        return curedProb;
    }

    public void setCuredProb(String curedProb) {
        this.curedProb = curedProb;
    }

    public String getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(String costMoney) {
        this.costMoney = costMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disease disease = (Disease) o;
        return Objects.equals(name, disease.name) &&
                Objects.equals(prevent, disease.prevent) &&
                Objects.equals(cause, disease.cause) &&
                Objects.equals(yibao, disease.yibao) &&
                Objects.equals(getProb, disease.getProb) &&
                Objects.equals(easyGet, disease.easyGet) &&
                Objects.equals(cureLastTime, disease.cureLastTime) &&
                Objects.equals(curedProb, disease.curedProb) &&
                Objects.equals(costMoney, disease.costMoney);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
