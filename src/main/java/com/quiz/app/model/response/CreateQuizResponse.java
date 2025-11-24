package com.quiz.app.model.response;

public record CreateQuizResponse(String name, String message, Long id, int numberOfQuestions) {

}
