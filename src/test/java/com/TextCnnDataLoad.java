package com;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.DataSetPreProcessor;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextCnnDataLoad implements DataSetIterator {

    /**
     * 数据集
     */
    private List<List<Integer>> dataList;


    /**
     * 标签
     */
    private List<Integer> labelList;


    /**
     * 类别数目
     */
    private int classNum;

    /**
     * 指针
     */
    private int point;

    /**
     * 下标位置列表
     */
    private List<Integer> index;


    /**
     * 样本数目
     */
    private int instanceNum;

    /**
     * 匹次大小
     */
    private int batchSize;

    private int minLen;

    private int maxLen;

    public TextCnnDataLoad(List<List<Integer>> dataList,
                           List<Integer> labelList,
                           int classNum,
                           int batchSize,
                           int minLen,
                           int maxLen) {
        this.dataList = dataList;
        this.labelList = labelList;
        this.classNum = classNum;
        this.batchSize = batchSize;

        this.minLen = minLen;
        this.maxLen = maxLen;

        this.instanceNum = dataList.size();
        this.point = 0;
        this.index = new ArrayList<>();
        for (int i = 0; i < instanceNum; i++)
            this.index.add(i);
        Collections.shuffle(this.index);
    }

    @Override
    public DataSet next(int i) {

        int start = this.point;
        int end = start + i < this.instanceNum ? start + i : this.instanceNum;
        List<List<Integer>> batchData = new ArrayList<>();
        List<Integer> batchLables = new ArrayList<>();
        // 句子的长度
        int batchSequenceLength = Integer.MIN_VALUE;
        for (int index = start; index < end; index++) {
            List<Integer> data = dataList.get(this.index.get(index));
            batchData.add(data);
            batchLables.add(labelList.get(this.index.get(index)));
            if (data.size() > batchSequenceLength) {
                batchSequenceLength = data.size();
            }
        }
        batchSequenceLength = Math.max(Math.min(batchSequenceLength, this.maxLen), this.minLen);
        int miniBatchSize = batchData.size();
        System.out.println(miniBatchSize + "##########################" + batchSequenceLength);
        INDArray features = Nd4j.zeros(miniBatchSize, batchSequenceLength);
        INDArray labels = Nd4j.zeros(miniBatchSize, this.classNum);
        for (int j = 0; j < miniBatchSize; j++) {
            List<Integer> data = batchData.get(j);
            List<Integer> localData = new ArrayList<>(data);
            // 此处竟然是float类型 真他妈奇怪
            float[] wordIds = new float[batchSequenceLength];
            for (int index = 0; index < batchSequenceLength; index++) {
                if (index < data.size()) {
                    wordIds[index] = localData.get(index);
                } else {
                    wordIds[index] = 0;
                }
            }
            features.putRow(j, Nd4j.create(wordIds));
            labels.putScalar(j, batchLables.get(j), 1.0);
        }
        this.point = this.point + miniBatchSize;
        return new DataSet(features, labels);
    }

    @Override
    public int inputColumns() {
        return 0;
    }

    @Override
    public int totalOutcomes() {
        return 0;
    }

    @Override
    public boolean resetSupported() {
        return true;
    }

    @Override
    public boolean asyncSupported() {
        return true;
    }

    @Override
    public void reset() {
        this.point = 0;
        Collections.shuffle(this.index);
    }

    @Override
    public int batch() {
        return batchSize;
    }

    @Override
    public void setPreProcessor(DataSetPreProcessor dataSetPreProcessor) {

    }

    @Override
    public DataSetPreProcessor getPreProcessor() {
        return null;
    }

    @Override
    public List<String> getLabels() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return this.point < this.instanceNum;
    }

    @Override
    public DataSet next() {
        return this.next(this.batchSize);
    }
}
