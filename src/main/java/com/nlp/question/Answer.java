package com.nlp.question;


import com.nlp.segment.nature.Nature;
import com.repository.QuestionRepository;

import java.util.List;

public interface Answer {

    /***
     * 通过查询 给出具体的答案
     * */
    public String answer(List<Nature> natures, QuestionRepository questionRepository);

}
