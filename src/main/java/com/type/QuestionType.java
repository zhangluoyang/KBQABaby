package com.type;

/***
 * 问题的类型
 * */
public enum QuestionType {

    DrugProduct("药名的药品", 0),

    CanCheckDisease("可以检查出来的疾病", 1),

    NotEat("不吃什么", 2),

    DoEat("吃什么", 3),

    EasyGetDiseasePerson("容易得病的人群", 4),

    DiseaseTrans("疾病的传播", 5),

    DiseaseReason("疾病的原因", 6),

    DiseaseSyndrome("疾病的并发症", 7),

    DiseaseCuteRadio("疾病治愈的概率", 8),

    DiseaseCuteTime("疾病治愈的时间", 9),

    DiseaseCuteMethod("疾病治愈的方法", 10),

    DiseaseCuteCost("疾病治愈的花费", 11),

    DiseaseSymptom("疾病的症状", 12),

    DiseasePrevention("疾病的预防措施", 13),

    DiseaseCheck("疾病的检查项目", 14),

    DiseaseDrug("疾病吃啥药", 15),

    SymptomDisease("症状疾病", 16),

    Known("未知", 17);


    private String name;

    private int index;

    QuestionType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static QuestionType parser(int index) {
        for (QuestionType type : QuestionType.values()) {
            if (type.index == index) {
                return type;
            }
        }
        return Known;
    }

    public static QuestionType parser(String name) {
        for (QuestionType type : QuestionType.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return Known;
    }

}
