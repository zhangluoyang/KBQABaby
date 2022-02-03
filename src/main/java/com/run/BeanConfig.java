package com.run;

import com.nlp.predict.impl.SentencePredict;
import com.nlp.process.Process;
import com.nlp.process.impl.SentenceNormProcess;
import com.nlp.question.Answer;
import com.nlp.question.impl.*;
import com.type.QuestionType;
import com.utils.FileUtils;
import com.nlp.segment.Segment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nlp.segment.impl.MatchSegment;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    /**
     * 词典路径
     */
    @Value("${vocabPath}")
    private String vocabPath;

    @Bean
    public Segment segment() throws FileNotFoundException {
        return new MatchSegment(FileUtils.getSpringPath(vocabPath));
    }

    @Value("${vocab}")
    private String vocab;

    @Value("${minLen}")
    private int minLen;

    @Value("${maxLen}")
    private int maxLen;

    @Value("${modelPath}")
    private String modelPath;


    @Bean
    public Process sentenceProcess() throws FileNotFoundException {
        List<String> lines = FileUtils.readLines(FileUtils.getSpringPath(vocab));
        Map<String, Integer> mapDict = FileUtils.strMapToId(lines);
        return new SentenceNormProcess(new MatchSegment(FileUtils.getSpringPath(vocabPath)), mapDict, minLen, maxLen);
    }

    @Bean
    Map<QuestionType, Answer> answer() {
        Map<QuestionType, Answer> answerMap = new HashMap<>();
        answerMap.put(QuestionType.DrugProduct, new DrugProductAnswer());
        answerMap.put(QuestionType.CanCheckDisease, new CanCheckDiseaseAnswer());
        answerMap.put(QuestionType.NotEat, new NotEatAnswer());
        answerMap.put(QuestionType.DoEat, new DoEatAnswer());
        answerMap.put(QuestionType.EasyGetDiseasePerson, new EasyGetDiseasePersonAnswer());
        answerMap.put(QuestionType.DiseaseTrans, new DiseaseTransAnswer());
        answerMap.put(QuestionType.DiseaseReason, new DiseaseReasonAnswer());
        answerMap.put(QuestionType.DiseaseSymptom, new DiseaseSyndromeAnswer());
        answerMap.put(QuestionType.DiseaseCuteRadio, new DiseaseCuteRadioAnswer());
        answerMap.put(QuestionType.DiseaseCuteTime, new DiseaseCuteTime());
        answerMap.put(QuestionType.DiseaseCuteMethod, new DiseaseCuteMethodAnswer());
        answerMap.put(QuestionType.DiseaseCuteCost, new DiseaseCuteCost());
        answerMap.put(QuestionType.DiseasePrevention, new DiseasePrevention());
        answerMap.put(QuestionType.DiseaseCheck, new DiseaseCheck());
        answerMap.put(QuestionType.DiseaseDrug, new DiseaseDrugAnswer());
        answerMap.put(QuestionType.SymptomDisease, new SymptomDiseaseAnswer());
        return answerMap;
    }

    @Bean
    SentencePredict sentencePredict() throws IOException {
        ComputationGraph graph = ComputationGraph.load(new File(modelPath), false);
        return new SentencePredict(graph);
    }

}
