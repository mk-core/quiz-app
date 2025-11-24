package com.quiz.app.exception;

public class QuizAppException extends Exception {

	public QuizAppException(Exception e, String message) {
		super(message, e);
	}
	
	public QuizAppException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
