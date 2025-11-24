package com.quiz.app.model.request;

import java.util.Set;

public record CreateQuizRequest(
		String name,
		String category,
		Set<QQ> questions) {

}
