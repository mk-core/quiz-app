package com.quiz.app.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.quiz.app.exception.QuizAppException;
import com.quiz.app.model.response.AppErrorResponse;

@RestControllerAdvice
public class AppExceptionHandler {
	
	
	@ExceptionHandler(exception = QuizAppException.class)
	public ResponseEntity<AppErrorResponse> handleQuizAppException(QuizAppException ex) {
		return ResponseEntity.status(500).body(new AppErrorResponse(ex.getMessage(), 500));
	}

	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<AppErrorResponse> handleException(Exception ex) {
		return ResponseEntity.status(500).body(new AppErrorResponse(ex.getMessage(), 500));
	}
}
