package com.nlp.question.impl;

import com.nlp.question.Answer;
import com.nlp.segment.nature.Nature;
import com.nlp.segment.nature.NatureEnum;
import com.repository.QuestionRepository;
import com.utils.ObjectUtils;

import java.util.List;

public class DiseaseCuteTime implements Answer {
    @Override
    public String answer(List<Nature> natures, QuestionRepository questionRepository) {
        List<Nature> diseases = ObjectUtils.getTerm(natures, NatureEnum.Disease);
        if (!ObjectUtils.isEmpty(diseases)) {
            return "" + questionRepository.getCureTime(diseases.get(0).getWord());
        }
        return null;
    }
}
