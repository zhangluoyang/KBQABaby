package com.nlp.question.impl;

import com.nlp.question.Answer;
import com.nlp.segment.nature.Nature;
import com.nlp.segment.nature.NatureEnum;
import com.repository.QuestionRepository;
import com.utils.ObjectUtils;

import java.util.Arrays;
import java.util.List;

public class DiseaseSyndromeAnswer implements Answer {
    @Override
    public String answer(List<Nature> natures, QuestionRepository questionRepository) {
        List<Nature> diseases = ObjectUtils.getTerm(natures, NatureEnum.Disease);
        if (!ObjectUtils.isEmpty(diseases)) {
            List<String> syndromes = questionRepository.getSymptoms(diseases.get(0).getWord());
            return Arrays.toString(syndromes.toArray());
        }
        return null;
    }
}
