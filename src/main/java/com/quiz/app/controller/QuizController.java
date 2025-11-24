package com.quiz.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.Service.QuizService;
import com.quiz.app.entity.Quiz;
import com.quiz.app.exception.QuizAppException;
import com.quiz.app.model.request.QuizRequest;
import com.quiz.app.model.response.QuizResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

	private final QuizService quizService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<QuizResponse> createQuiz(@RequestBody QuizRequest quzRequest) throws QuizAppException {
		Quiz quiz = quizService.createQuiz(quzRequest);
		return ResponseEntity.ok().body(new QuizResponse(quiz.getName(), "Quiz Created Successfully!", quiz.getId(),
				quiz.getQuestions().size()));
	}
}
