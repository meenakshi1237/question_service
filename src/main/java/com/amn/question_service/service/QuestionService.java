package com.amn.question_service.service;

import com.amn.question_service.dao.QuestionDao;
import com.amn.question_service.entity.Question;
import com.amn.question_service.entity.dto.QuestionWrapper;
import com.amn.question_service.entity.dto.Result;
import com.amn.question_service.utils.ApiResponse;
import com.amn.question_service.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionDao questionDao;

    private final ModelMapper modelMapper;
    public ResponseEntity<ApiResponse> getAllQuestions(){
        return questionDao.getAllQuestions();
    }

    public ResponseEntity<ApiResponse> getQuestiosByCategory(String category) {
        return questionDao.getQuestionsByCategory(category);
    }

    public ResponseEntity<ApiResponse> addQuestion(Question question) {
        return questionDao.addQuestion(question);
    }
    //------------------------------------------------------------
    public ResponseEntity<ApiResponse> getQuestionsForQuiz(String category, Integer noQ) {
        return questionDao.getQuestionsForQuiz(category,noQ);
    }

    public ResponseEntity<ApiResponse> getQuestionsFromIds(List<Integer> questionIds) {
        List<Question> questions=questionDao.getQuestionsFromIds(questionIds);
        List<QuestionWrapper> userQuestions=new ArrayList<>();
        for(Question qw:questions){
            QuestionWrapper  questionWrapper=modelMapper.map(qw,QuestionWrapper.class);
            userQuestions.add(questionWrapper);
        }
        return ResponseUtils.getOkResponse(userQuestions);
    }

    public ResponseEntity<ApiResponse> caliclateResults(List<Result> answers) {
        int score=0;
        for(Result result:answers){
            Question question=questionDao.findQuestionById(result.getId());
            if(question.getCorrectAnswer().equalsIgnoreCase(result.getAnswer())){
                score++;
            }
        }
        return ResponseUtils.getOkResponse(score);
    }
}
