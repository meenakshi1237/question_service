package com.amn.question_service.Exception;

public class QuestionNotFoundException extends RuntimeException{
    @Override
    public String getMessage(){
        return "Question Not Found With Given Id";
    }
}
