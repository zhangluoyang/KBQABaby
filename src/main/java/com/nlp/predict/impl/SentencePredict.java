package com.nlp.predict.impl;

import com.nlp.predict.Predict;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.nd4j.linalg.api.ndarray.INDArray;


public class SentencePredict implements Predict {

    // 加载模型
    private ComputationGraph graph;

    public SentencePredict(ComputationGraph graph) {
        this.graph = graph;
    }

    @Override
    public INDArray predict(INDArray feature) {
        return this.graph.output(feature)[0];
    }
}
