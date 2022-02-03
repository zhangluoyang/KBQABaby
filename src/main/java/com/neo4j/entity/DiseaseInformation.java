package com.neo4j.entity;

import java.io.Serializable;
import java.util.List;

public class DiseaseInformation implements Serializable {

    /**
     * 疾病名称
     */
    private String name;

    /**
     * 疾病描述
     */
    private String desc;

    /**
     * 疾病类别
     */
    private List<String> category;

    /**
     * 预防措施
     */
    private String prevent;

    /**
     * 诱因
     */
    private String cause;

    /**
     * 症状
     */
    private List<String> symptom;


    /**
     * 医保
     */
    private String yibao_status;

    /**
     * 得病率
     */
    private String get_prob;

    /**
     * 易得病群体
     */
    private String easy_get;

    /**
     * 传播方式
     */
    private String get_way;

    /**
     * 伴随症状
     */
    private List<String> acompany;

    /**
     * 科室
     */
    private List<String> cure_department;

    /**
     * 治疗途径
     */
    private List<String> cure_way;

    /**
     * 治疗时间
     */
    private String cure_lasttime;

    /**
     * 治愈率
     */
    private String cured_prob;

    /**
     * 推荐药品
     */
    private List<String> common_drug;

    /**
     * 花费
     */
    private String cost_money;

    /**
     * 检查
     */
    private List<String> check;


    /**
     * 益食
     */
    private List<String> do_eat;

    /**
     * 忌吃
     */
    private List<String> not_eat;

    /**
     * 推荐食物
     */
    private List<String> recommand_eat;

    /**
     * 推荐药品
     */
    private List<String> recommand_drug;

    /**
     * 详细药品
     */
    private List<String> drug_detail;


    public DiseaseInformation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
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

    public List<String> getSymptom() {
        return symptom;
    }

    public void setSymptom(List<String> symptom) {
        this.symptom = symptom;
    }

    public String getYibao_status() {
        return yibao_status;
    }

    public void setYibao_status(String yibao_status) {
        this.yibao_status = yibao_status;
    }

    public String getGet_prob() {
        return get_prob;
    }

    public void setGet_prob(String get_prob) {
        this.get_prob = get_prob;
    }

    public String getEasy_get() {
        return easy_get;
    }

    public void setEasy_get(String easy_get) {
        this.easy_get = easy_get;
    }

    public String getGet_way() {
        return get_way;
    }

    public void setGet_way(String get_way) {
        this.get_way = get_way;
    }

    public List<String> getAcompany() {
        return acompany;
    }

    public void setAcompany(List<String> acompany) {
        this.acompany = acompany;
    }

    public List<String> getCure_department() {
        return cure_department;
    }

    public void setCure_department(List<String> cure_department) {
        this.cure_department = cure_department;
    }

    public List<String> getCure_way() {
        return cure_way;
    }

    public void setCure_way(List<String> cure_way) {
        this.cure_way = cure_way;
    }

    public String getCure_lasttime() {
        return cure_lasttime;
    }

    public void setCure_lasttime(String cure_lasttime) {
        this.cure_lasttime = cure_lasttime;
    }

    public String getCured_prob() {
        return cured_prob;
    }

    public void setCured_prob(String cured_prob) {
        this.cured_prob = cured_prob;
    }

    public List<String> getCommon_drug() {
        return common_drug;
    }

    public void setCommon_drug(List<String> common_drug) {
        this.common_drug = common_drug;
    }

    public String getCost_money() {
        return cost_money;
    }

    public void setCost_money(String cost_money) {
        this.cost_money = cost_money;
    }

    public List<String> getCheck() {
        return check;
    }

    public void setCheck(List<String> check) {
        this.check = check;
    }

    public List<String> getDo_eat() {
        return do_eat;
    }

    public void setDo_eat(List<String> do_eat) {
        this.do_eat = do_eat;
    }

    public List<String> getNot_eat() {
        return not_eat;
    }

    public void setNot_eat(List<String> not_eat) {
        this.not_eat = not_eat;
    }

    public List<String> getRecommand_eat() {
        return recommand_eat;
    }

    public void setRecommand_eat(List<String> recommand_eat) {
        this.recommand_eat = recommand_eat;
    }

    public List<String> getRecommand_drug() {
        return recommand_drug;
    }

    public void setRecommand_drug(List<String> recommand_drug) {
        this.recommand_drug = recommand_drug;
    }

    public List<String> getDrug_detail() {
        return drug_detail;
    }

    public void setDrug_detail(List<String> drug_detail) {
        this.drug_detail = drug_detail;
    }

    @Override
    public String toString() {
        return "DiseaseInformation{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", category=" + category +
                ", prevent=" + prevent +
                ", cause='" + cause + '\'' +
                ", symptom=" + symptom +
                ", yibao_status='" + yibao_status + '\'' +
                ", get_prob='" + get_prob + '\'' +
                ", easy_get='" + easy_get + '\'' +
                ", get_way='" + get_way + '\'' +
                ", acompany='" + acompany + '\'' +
                ", cure_department=" + cure_department +
                ", cure_way=" + cure_way +
                ", cure_lasttime='" + cure_lasttime + '\'' +
                ", cured_prob='" + cured_prob + '\'' +
                ", common_drug=" + common_drug +
                ", cost_money='" + cost_money + '\'' +
                ", check=" + check +
                ", do_eat=" + do_eat +
                ", not_eat=" + not_eat +
                ", recommand_eat=" + recommand_eat +
                ", recommand_drug=" + recommand_drug +
                ", drug_detail=" + drug_detail +
                '}';
    }
}
