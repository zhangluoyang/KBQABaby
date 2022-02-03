package com.service.question.impl;

import com.nlp.predict.impl.SentencePredict;
import com.nlp.process.Process;
import com.nlp.question.Answer;
import com.nlp.segment.nature.Nature;
import com.repository.QuestionRepository;
import com.service.question.QuestionService;
import com.type.QuestionType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Primary
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    public QuestionRepository questionRepository;

    @Autowired
    public Process sentenceProcess;

    @Autowired
    public SentencePredict sentencePredict;

    @Autowired
    Map<QuestionType, Answer> answer;

    @Override
    public String answer(String question) {
        List<Nature> natures = new ArrayList<>();
        List<Integer> segmentsId = new ArrayList<>();
        String normSentnece = sentenceProcess.process(question, segmentsId, natures);
        INDArray feature = this.sentenceProcess.getFeature(segmentsId);
        INDArray output = sentencePredict.predict(feature);
        int predict = output.argMax(-1).toIntVector()[0];
        QuestionType type = QuestionType.parser(predict);
        String result = null;
        switch (type) {
            case DrugProduct:
                result = answer.get(QuestionType.DrugProduct).answer(natures, this.questionRepository);
                break;
            case CanCheckDisease:
                result = answer.get(QuestionType.CanCheckDisease).answer(natures, this.questionRepository);
                break;
            case NotEat:
                result = answer.get(QuestionType.NotEat).answer(natures, this.questionRepository);
                break;
            case DoEat:
                result = answer.get(QuestionType.DoEat).answer(natures, this.questionRepository);
                break;
            case EasyGetDiseasePerson:
                result = answer.get(QuestionType.EasyGetDiseasePerson).answer(natures, this.questionRepository);
                break;
            case DiseaseTrans:
                result = answer.get(QuestionType.DiseaseTrans).answer(natures, this.questionRepository);
                break;
            case DiseaseReason:
                result = answer.get(QuestionType.DiseaseReason).answer(natures, this.questionRepository);
                break;
            case DiseaseSyndrome:
                result = answer.get(QuestionType.DiseaseSyndrome).answer(natures, this.questionRepository);
                break;
            case DiseaseCuteRadio:
                result = answer.get(QuestionType.DiseaseCuteRadio).answer(natures, this.questionRepository);
                break;
            case DiseaseCuteTime:
                result = answer.get(QuestionType.DiseaseCuteTime).answer(natures, this.questionRepository);
                break;
            case DiseaseCuteMethod:
                result = answer.get(QuestionType.DiseaseCuteMethod).answer(natures, this.questionRepository);
                break;
            case DiseaseCuteCost:
                result = answer.get(QuestionType.DiseaseCuteCost).answer(natures, this.questionRepository);
                break;
            case DiseaseSymptom:
                result = answer.get(QuestionType.DiseaseSymptom).answer(natures, this.questionRepository);
                break;
            case DiseasePrevention:
                result = answer.get(QuestionType.DiseasePrevention).answer(natures, this.questionRepository);
                break;
            case DiseaseCheck:
                result = answer.get(QuestionType.DiseaseCheck).answer(natures, this.questionRepository);
                break;
            case DiseaseDrug:
                result = answer.get(QuestionType.DiseaseDrug).answer(natures, this.questionRepository);
                break;
            case SymptomDisease:
                result = answer.get(QuestionType.SymptomDisease).answer(natures, this.questionRepository);
                break;
        }
        return result;
    }
}
