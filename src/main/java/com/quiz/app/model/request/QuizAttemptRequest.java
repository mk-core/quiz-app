package com.quiz.app.model.request;

import java.util.Set;

public record QuizAttemptRequest(
		Long quizId,
		Set<AttemptQuestion> questions) {

}
