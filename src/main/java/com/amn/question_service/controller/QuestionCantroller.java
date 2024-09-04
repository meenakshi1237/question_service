package com.amn.question_service.controller;

import com.amn.question_service.entity.Question;
import com.amn.question_service.entity.dto.Result;
import com.amn.question_service.service.QuestionService;
import com.amn.question_service.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionCantroller {
    private final QuestionService questionService;
    private final Environment environment;

    @GetMapping("allquestions")
    public ResponseEntity<ApiResponse> allQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<ApiResponse> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestiosByCategory(category);
    }
    @PostMapping("addquestion")
    public ResponseEntity<ApiResponse> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
    //In order to make Question service independent we should not allow quiz service to question service
    //quize required Questions to generate quiz and
    //it requires questions based on quiz id to caliclate results
    //the create Quiz api will be present in quiz service but it will request to question service where question service will create and gives question ids
    //we are giving only ids beacuse however all the things related to questions we are doing in question service only

    //1.Genarete Quiz
    @GetMapping("generate")
        public ResponseEntity<ApiResponse> getQuestionsForQuiz(@RequestParam String category,@RequestParam Integer noQ){
        System.err.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsForQuiz(category,noQ);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<ApiResponse> getQuestionsFromIds(@RequestBody List<Integer> questionIds){
        System.err.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromIds(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<ApiResponse> caliclateResults(@RequestBody List<Result> answers){
        return questionService.caliclateResults(answers);
    }
}
