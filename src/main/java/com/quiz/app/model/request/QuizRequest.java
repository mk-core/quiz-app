package com.quiz.app.model.request;

import java.util.Set;

public record QuizRequest(
		String name,
		String category,
		Set<QQ> questions) {

}
