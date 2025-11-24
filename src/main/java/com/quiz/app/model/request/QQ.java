package com.quiz.app.model.request;

public record QQ(String question, String level, String option1, String option2, String option3, String option4,
		boolean isMcq, boolean isBoolean, boolean isText, String correctAnswer) {

}