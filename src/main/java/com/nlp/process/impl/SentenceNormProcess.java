package com.nlp.process.impl;

import com.nlp.process.Process;
import com.nlp.segment.Segment;
import com.nlp.segment.nature.Nature;
import com.nlp.segment.nature.NatureEnum;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.List;
import java.util.Map;

public class SentenceNormProcess implements Process {

    /**
     * 分词器
     */
    Segment segment;

    /**
     * 词典
     */
    private Map<String, Integer> mapDict;
    /**
     * 最小长度
     */
    private int minLen;
    /**
     * 最大长度
     */
    private int maxLen;


    public SentenceNormProcess(Segment segment, Map<String, Integer> mapDict, int minLen, int maxLen) {
        this.segment = segment;
        this.mapDict = mapDict;
        this.minLen = minLen;
        this.maxLen = maxLen;
    }

    @Override
    public String process(String sentence,
                          List<Integer> segmentsId,
                          List<Nature> natures) {
        String normSentence = this.normProcess(sentence, natures);
        int length = Math.min(Math.max(normSentence.length(), minLen), maxLen);
        for (int i = 0; i < length; i++) {
            if (i < normSentence.length()) {
                segmentsId.add(mapDict.getOrDefault(String.valueOf(normSentence.charAt(i)), 0));
            } else {
                segmentsId.add(0);
            }
        }
        return normSentence;
    }

    public String normProcess(String sentence, List<Nature> natures) {
        List<Nature> natureList = segment.run(sentence);
        natures.addAll(natureList);
        StringBuilder builder = new StringBuilder();
        for (Nature nature : natureList) {
            if (!nature.getNatureEnum().equals(NatureEnum.Other)) {
                builder.append(nature.getNatureEnum().getTag());
            } else {
                builder.append(nature.getWord());
            }
        }
        return builder.toString();
    }

    @Override
    public INDArray getFeature(List<Integer> segmentsId) {
        INDArray features = Nd4j.zeros(1, maxLen);
        float[] wordIds = new float[maxLen];
        for (int index = 0; index < maxLen; index++) {
            if (index < segmentsId.size()) {
                wordIds[index] = segmentsId.get(index);
            } else {
                wordIds[index] = 0;
            }
        }
        features.putRow(1, Nd4j.create(wordIds));
        return features;
    }

}
