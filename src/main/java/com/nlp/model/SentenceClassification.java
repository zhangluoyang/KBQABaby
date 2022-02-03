package com.nlp.model;

import org.deeplearning4j.nn.conf.ComputationGraphConfiguration;
import org.deeplearning4j.nn.conf.ConvolutionMode;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.graph.MergeVertex;
import org.deeplearning4j.nn.conf.layers.*;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.springframework.stereotype.Component;

/**
 * 句子分类相关
 */
public class SentenceClassification {


    /**
     * @param vocabSize     词典数目
     * @param cSize         通道数目
     * @param embeddingSize
     * @param labelSize     标签数目
     */
    public static ComputationGraph getTextCnn(int vocabSize,
                                              int cSize,
                                              int embeddingSize,
                                              int labelSize) {
        ComputationGraphConfiguration config = new NeuralNetConfiguration.Builder()
                .weightInit(WeightInit.RELU)
                .activation(Activation.LEAKYRELU)
                .updater(new Adam(0.01))
                .convolutionMode(ConvolutionMode.Same)
                .l2(0.0001)
                .graphBuilder()
                .addInputs("input")
                .addLayer("embedding", new EmbeddingSequenceLayer.Builder()
                        .nIn(vocabSize).nOut(embeddingSize).build(), "input")
                .addLayer("2-gram", new Convolution1DLayer.Builder()
                        .kernelSize(2).stride(1).nIn(embeddingSize).nOut(cSize).build(), "embedding")
                .addLayer("3-gram", new Convolution1DLayer.Builder()
                        .kernelSize(3).stride(1).nIn(embeddingSize).nOut(cSize).build(), "embedding")
                .addLayer("4-gram", new Convolution1DLayer.Builder()
                        .kernelSize(4).stride(1).nIn(embeddingSize).nOut(cSize).build(), "embedding")
                .addVertex("merge", new MergeVertex(), "2-gram", "3-gram", "4-gram")
                .addLayer("globalPool", new GlobalPoolingLayer.Builder().poolingType(PoolingType.MAX)
                        .dropOut(0.5).build(), "merge")
                .addLayer("out", new OutputLayer.Builder()
                        .lossFunction(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX)
                        .nIn(3 * cSize).nOut(labelSize)
                        .build(), "globalPool")
                .setOutputs("out").build();
        ComputationGraph net = new ComputationGraph(config);
        net.init();
        return net;
    }

}
