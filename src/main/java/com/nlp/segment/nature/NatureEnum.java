package com.nlp.segment.nature;

public enum NatureEnum {
    /**
     * 词性
     */

    Disease("疾病", "#疾病#"),

    DiseaseCheck("体检项目", "#体检项目#"),

    Symptom("症状", "#症状#"),

    Department("门诊科", "#门诊科#"),

    Drug("药品", "#药品#"),

    DrugProduct("药品产品", "#药品产品#"),

    Food("食物", "#食物#"),

    Other("其它", "#其它#");

    private String name;

    private String tag;


    @Override
    public String toString() {
        return "NatureEnum{" +
                "name='" + name + '\'' +
                '}';
    }

    NatureEnum(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

    public static NatureEnum parser(String name) {
        for (NatureEnum type : NatureEnum.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return Other;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }
}
