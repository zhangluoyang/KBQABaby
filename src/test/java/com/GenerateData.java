package com;

import com.alibaba.fastjson.JSON;
import com.neo4j.entity.DiseaseInformation;
import com.neo4j.entity.impl.*;
import com.utils.FileUtils;
import com.utils.O;

import java.util.ArrayList;
import java.util.List;

public class GenerateData {

    public static void main(String[] args) {

        String resourcesPath = System.getProperty("user.dir").replace("\\", "/")
                + "/src/main/resources";
        String vocabRootPath = resourcesPath + "/vocab";

        String diseasePath = vocabRootPath + "/疾病.txt";
        String diseaseCheckPath = vocabRootPath + "/体检项目.txt";
        String symptomPath = vocabRootPath + "/症状.txt";
        String drugPath = vocabRootPath + "/药品.txt";
        String drugProductPath = vocabRootPath + "/药品产品.txt";
        String departmentPath = vocabRootPath + "/门诊科.txt";
        String foodPath = vocabRootPath + "/食物.txt";

        String dataPath = resourcesPath + "/medical.json";

        List<String> lines = FileUtils.readLines(dataPath);

        // 词典
        List<String> diseaseWords = new ArrayList<>();
        List<String> diseaseCheckWords = new ArrayList<>();
        List<String> symptomWords = new ArrayList<>();
        List<String> drugWords = new ArrayList<>();
        List<String> drugProductWords = new ArrayList<>();
        List<String> departments = new ArrayList<>();
        List<String> foodWords = new ArrayList<>();


        for (String line : lines) {
            DiseaseInformation info = JSON.parseObject(line, DiseaseInformation.class);
            // 疾病节点
            Disease disease = Disease.create(info.getName(), info.getPrevent(), info.getCause(), info.getYibao_status(), info.getGet_prob(),
                    info.getEasy_get(), info.getCure_lasttime(), info.getCured_prob(), info.getCost_money());

            if (!O.empty(info.getName())) {
                diseaseWords.add(info.getName());
            }
            if (!O.empty(info.getCheck())) {
                diseaseCheckWords.addAll(info.getCheck());
            }
            if (!O.empty(info.getSymptom())) {
                symptomWords.addAll(info.getSymptom());
            }
            if (!O.empty(info.getRecommand_drug())) {
                drugWords.addAll(info.getRecommand_drug());
            }
            if (!O.empty(info.getDrug_detail())) {
                for (String detail : info.getDrug_detail()) {
                    String[] split = detail.replace(")", "").split("\\(");
                    if (split.length > 1) {
                        String drupProduct = split[0];
                        drugProductWords.add(drupProduct);
                    }
                }
            }
            if (!O.empty(info.getCure_department())) {
                departments.addAll(info.getCure_department());
            }
            if (!O.empty(info.getNot_eat())) {
                foodWords.addAll(info.getNot_eat());
            }
            if (!O.empty(info.getDo_eat())) {
                foodWords.addAll(info.getDo_eat());
            }
        }

        // 词典写文件
        FileUtils.writeLines(diseaseWords, diseasePath);
        FileUtils.writeLines(diseaseCheckWords, diseaseCheckPath);
        FileUtils.writeLines(symptomWords, symptomPath);
        FileUtils.writeLines(drugWords, drugPath);
        FileUtils.writeLines(drugProductWords, drugProductPath);
        FileUtils.writeLines(departments, departmentPath);
        FileUtils.writeLines(foodWords, foodPath);

    }

}
