package com.nlp.question.impl;

import com.nlp.question.Answer;
import com.nlp.segment.nature.Nature;
import com.nlp.segment.nature.NatureEnum;
import com.repository.QuestionRepository;
import com.utils.ObjectUtils;

import java.util.Arrays;
import java.util.List;

public class CanCheckDiseaseAnswer implements Answer {

    @Override
    public String answer(List<Nature> natures, QuestionRepository questionRepository) {
        List<Nature> checks = ObjectUtils.getTerm(natures, NatureEnum.DiseaseCheck);
        if (!ObjectUtils.isEmpty(checks)) {
            List<String> diseases = questionRepository.getCanCheckDisease(checks.get(0).getWord());
            return Arrays.toString(diseases.toArray());
        }
        return null;
    }

}
