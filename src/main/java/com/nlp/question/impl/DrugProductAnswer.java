package com.nlp.question.impl;

import com.nlp.question.Answer;
import com.nlp.segment.nature.Nature;
import com.nlp.segment.nature.NatureEnum;
import com.repository.QuestionRepository;
import com.utils.ObjectUtils;

import java.util.Arrays;
import java.util.List;

public class DrugProductAnswer implements Answer {

    @Override
    public String answer(List<Nature> natures, QuestionRepository questionRepository) {
        List<Nature> drugs = ObjectUtils.getTerm(natures, NatureEnum.Drug);
        if (!ObjectUtils.isEmpty(drugs)) {
            List<String> products = questionRepository.getDrugProduct(drugs.get(0).getWord());
            return Arrays.toString(products.toArray());
        }
        return null;
    }

}
