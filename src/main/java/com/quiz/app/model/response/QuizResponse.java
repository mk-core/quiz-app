package com.quiz.app.model.response;

public record QuizResponse(
		Long quizId,
		String question,
		String level,
		String option1,
		String option2,
		String option3,
		String option4) {

}
