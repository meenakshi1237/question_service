package com.amn.question_service.dao;

import com.amn.question_service.Exception.QuestionNotFoundException;
import com.amn.question_service.entity.Question;
import com.amn.question_service.repository.QuestionRepository;
import com.amn.question_service.utils.ApiResponse;
import com.amn.question_service.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDao {
    @Autowired
    private QuestionRepository questionRepository;

    public  List<Question> getQuestionsFromIds(List<Integer> questionIds) {
        return questionRepository.findAllById(questionIds);
    }

    public ResponseEntity<ApiResponse> getQuestionsForQuiz(String category, Integer noQ) {
        return ResponseUtils.getOkResponse(questionRepository.getQuestionsForQuiz(category,noQ));
    }

    public ResponseEntity<ApiResponse> getAllQuestions(){
        return ResponseUtils.getOkResponse(questionRepository.findAll());
    }

    public ResponseEntity<ApiResponse> getQuestionsByCategory(String category) {
        return ResponseUtils.getOkResponse(questionRepository.findByCategory(category));
    }

    public ResponseEntity<ApiResponse> addQuestion(Question question) {
        return ResponseUtils.getCreatedResponse(questionRepository.save(question));
    }

    public List<Question> getLimitedQuestionsByCategory(String category,int numQ){
        return questionRepository.getLimitedQuestionsByCategory(category,numQ);
    }

    public Question findQuestionById(Integer id) {
        return questionRepository.findById(id).orElseThrow(()->new QuestionNotFoundException());
    }
}
