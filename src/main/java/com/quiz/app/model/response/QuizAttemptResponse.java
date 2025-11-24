package com.quiz.app.model.response;

public record QuizAttemptResponse(

		Long correctAnswers, Long wrongAnswers, String quizAttemptStatus) {

}
