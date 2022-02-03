package com.controller;

import com.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @RequestMapping("/query")
    public String query(@RequestParam(value = "question") String question) throws Exception {
        return questionService.answer(question);
    }

}
