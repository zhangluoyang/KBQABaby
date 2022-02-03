package com.nlp.predict;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.springframework.boot.CommandLineRunner;

public interface Predict {

    INDArray predict(INDArray feature);

}
