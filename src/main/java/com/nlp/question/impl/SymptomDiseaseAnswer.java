package com.nlp.question.impl;

import com.nlp.question.Answer;
import com.nlp.segment.nature.Nature;
import com.nlp.segment.nature.NatureEnum;
import com.repository.QuestionRepository;
import com.utils.ObjectUtils;

import java.util.Arrays;
import java.util.List;

public class SymptomDiseaseAnswer implements Answer {

    @Override
    public String answer(List<Nature> natures, QuestionRepository questionRepository) {
        List<Nature> symptoms = ObjectUtils.getTerm(natures, NatureEnum.Symptom);
        if (!ObjectUtils.isEmpty(symptoms)) {
            List<String> diseases = questionRepository.getDisease(symptoms.get(0).getWord());
            return Arrays.toString(diseases.toArray());
        }
        return null;
    }

}
