package com.nlp.process;


import com.nlp.segment.nature.Nature;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.List;

public interface Process {

    /**
     * @param sentence   句子
     * @param segmentsId 字的Id
     * @param natures    词列表
     */
    String process(String sentence,
                   List<Integer> segmentsId,
                   List<Nature> natures);

    /**
     * 句子归一化
     *
     * @param sentence
     */
    String normProcess(String sentence, List<Nature> natures);


    /**
     * 特征
     *
     * @param
     */
    INDArray getFeature(List<Integer> segmentsId);
}
