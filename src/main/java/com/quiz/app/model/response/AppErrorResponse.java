package com.quiz.app.model.response;

public record AppErrorResponse(
		String message,
		int errorCode) {

}
