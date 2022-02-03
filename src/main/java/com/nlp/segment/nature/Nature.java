package com.nlp.segment.nature;

import java.io.Serializable;

public class Nature implements Serializable {

    private String word;

    private NatureEnum natureEnum;

    public Nature() {
    }

    public Nature(String word, NatureEnum natureEnum) {
        this.word = word;
        this.natureEnum = natureEnum;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public NatureEnum getNatureEnum() {
        return natureEnum;
    }

    public void setNatureEnum(NatureEnum natureEnum) {
        this.natureEnum = natureEnum;
    }

    @Override
    public String toString() {
        return "Nature{" +
                "word='" + word + '\'' +
                ", natureEnum=" + natureEnum +
                '}';
    }

    public static Nature create(String word, NatureEnum natureEnum) {
        return new Nature(word, natureEnum);
    }

}
